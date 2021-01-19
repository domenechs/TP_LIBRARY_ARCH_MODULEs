package fr.training.spring.library.batch.exportjob;

import fr.training.spring.library.batch.common.FullReportListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.SingleColumnRowMapper;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.Writer;

@Configuration
public class ExportLibraryJobConfig {
    @Autowired
    DataSource dataSource;
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    FullReportListener fullReportListener;

    @Bean(name="exportjob")
    public Job exportLibraryJob(){
        return jobBuilderFactory.get("export-job")
                                .incrementer(new RunIdIncrementer())
                                .start(exportLibraryStep(null,null))
                                .listener(fullReportListener)
                                .build();
    }

    @Bean(name="exportstep")
    public Step exportLibraryStep(final FlatFileItemWriter<LibraryDTO> writer, final LibraryProcessor libraryProcessor){
        return stepBuilderFactory.get("export-step")
                                    .<String,LibraryDTO>chunk(5)
                                    .reader(exportReader())
                                    .processor(libraryProcessor)
                                    .writer(writer)
                                    .build();
    }

    @Bean
    public JdbcCursorItemReader<String> exportReader(){
        final JdbcCursorItemReader jdbcCursorItemReader = new JdbcCursorItemReader();
        jdbcCursorItemReader.setDataSource(dataSource);
        jdbcCursorItemReader.setSql("SELECT identifiant FROM LIBRARY");
        jdbcCursorItemReader.setRowMapper(new SingleColumnRowMapper());
        return jdbcCursorItemReader;
    }

    @Bean
    @StepScope
    public FlatFileItemWriter<LibraryDTO> exportWriter(@Value("#{jobParameters['output-file']}") final String outputFile){
        final FlatFileItemWriter<LibraryDTO> flatFileItemWriter = new FlatFileItemWriter<>();
        flatFileItemWriter.setResource(new FileSystemResource(outputFile));
        final DelimitedLineAggregator<LibraryDTO> dtoDelimitedLineAggregator = new DelimitedLineAggregator<>();
        final BeanWrapperFieldExtractor<LibraryDTO> beanWrapperFieldExtractor = new BeanWrapperFieldExtractor<>();
        beanWrapperFieldExtractor.setNames(new String[]{"idLibrary","adresseNumero","adresseRue","adresseVille",
                "adresseCodePostal","directeurPrenom","directeurNom","type"} );
        dtoDelimitedLineAggregator.setFieldExtractor(beanWrapperFieldExtractor);
        dtoDelimitedLineAggregator.setDelimiter(";");
        flatFileItemWriter.setLineAggregator(dtoDelimitedLineAggregator);
        flatFileItemWriter.setHeaderCallback(new FlatFileHeaderCallback() {

            @Override
            public void writeHeader(final Writer writer) throws IOException {
                writer.write("Id library;numero;rue;ville;code postal;prenom directeur;nom directeur,type library");
            }
        });
        return flatFileItemWriter;
    }

}
