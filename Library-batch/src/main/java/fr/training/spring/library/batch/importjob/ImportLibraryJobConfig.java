package fr.training.spring.library.batch.importjob;

import fr.training.spring.library.batch.common.FullReportListener;
import fr.training.spring.library.batch.exportjob.LibraryDTO;
import fr.training.spring.library.domain.Adresse;
import fr.training.spring.library.domain.Directeur;
import fr.training.spring.library.domain.Library;
import fr.training.spring.library.domain.LibraryRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.adapter.ItemWriterAdapter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import java.util.ArrayList;


@Configuration
public class ImportLibraryJobConfig {
    @Autowired
    private FullReportListener fullReportListener;
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private LibraryRepository libraryRepository;

    @Bean(name = "importjob")
    public Job importLibraryJob(){
        return jobBuilderFactory.get("import-job")
                                .incrementer(new RunIdIncrementer())
                                .start(importLibraryStep())
                                .listener(fullReportListener)
                                .build();
    }

    @Bean(name="importstep")
    public Step importLibraryStep(){
        return stepBuilderFactory.get("import-step")
                .<LibraryDTO,Library>chunk(5)
                .reader(importLibraryReader(null))
                .processor(importLibraryProcessor())
                .writer(importLibraryWriter())
                .build();
    }

    @Bean
    @StepScope
    public FlatFileItemReader<LibraryDTO> importLibraryReader(@Value("#{jobParameters['input-file']}") final String inputFile){
        FlatFileItemReader flatFileItemReader = new FlatFileItemReader();
        BeanWrapperFieldSetMapper<LibraryDTO> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<>();
        beanWrapperFieldSetMapper.setTargetType(LibraryDTO.class);

        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
        delimitedLineTokenizer.setDelimiter(";");
        delimitedLineTokenizer.setNames(new String[]{"idLibrary","adresseNumero","adresseRue","adresseVille",
                "adresseCodePostal","directeurPrenom","directeurNom","type"});

        DefaultLineMapper<LibraryDTO> dtoDefaultLineMapper = new DefaultLineMapper<>();
        dtoDefaultLineMapper.setFieldSetMapper(beanWrapperFieldSetMapper);
        dtoDefaultLineMapper.setLineTokenizer(delimitedLineTokenizer);

        flatFileItemReader.setResource(new FileSystemResource(inputFile));
        flatFileItemReader.setLineMapper(dtoDefaultLineMapper);
        flatFileItemReader.setLinesToSkip(1);

        return flatFileItemReader;
    }


    private ItemProcessor<LibraryDTO,Library> importLibraryProcessor(){
        return new ItemProcessor<LibraryDTO, Library>() {
            @Override
            public Library process(LibraryDTO libraryDTO) throws Exception {
                return new Library(libraryDTO.getIdLibrary(),libraryDTO.getType(),
                        new Adresse(libraryDTO.getAdresseNumero(),libraryDTO.getAdresseRue(),
                                libraryDTO.getAdresseCodePostal(),libraryDTO.getAdresseVille()),
                        new Directeur(libraryDTO.getDirecteurPrenom(),libraryDTO.getDirecteurNom()),new ArrayList<>());
            }
        };
    }

    @Bean
    public ItemWriterAdapter<Library> importLibraryWriter(){
        ItemWriterAdapter<Library> itemWriterAdapter = new ItemWriterAdapter<>();
        itemWriterAdapter.setTargetMethod("save");
        itemWriterAdapter.setTargetObject(libraryRepository);
        return itemWriterAdapter;
    }
}
