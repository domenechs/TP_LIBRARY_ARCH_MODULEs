package fr.training.spring.library.batch.exportjob;

import fr.training.spring.library.application.LibraryService;
import fr.training.spring.library.domain.Library;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class LibraryProcessor implements ItemProcessor<String,LibraryDTO> {

    @Autowired
    private LibraryService libraryService;
    @Override
    public LibraryDTO process(String idLibrary) throws Exception {
        final Library library = libraryService.afficheDetailLibrary(idLibrary);

        final LibraryDTO libraryDTO = new LibraryDTO();
        libraryDTO.setIdLibrary(library.getId());
        libraryDTO.setAdresseCodePostal(library.getAdresse().getCodePostal());
        libraryDTO.setAdresseNumero(library.getAdresse().getNumero());
        libraryDTO.setAdresseRue(library.getAdresse().getRue());
        libraryDTO.setAdresseVille(library.getAdresse().getVille());
        libraryDTO.setDirecteurPrenom(library.getDirecteur().getPrenom());
        libraryDTO.setDirecteurNom(library.getDirecteur().getNom());
        libraryDTO.setType(library.getType());

        return libraryDTO;
    }
}
