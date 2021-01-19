package fr.training.spring.library.exposition;

import fr.training.spring.library.application.LibraryService;
import fr.training.spring.library.application.LivreService;
import fr.training.spring.library.domain.Library;
import fr.training.spring.library.domain.LibraryRepository;
import fr.training.spring.library.domain.Livre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LivreResource {

    @Autowired
    LivreService livreService;
    @Autowired
    LivreAdapter livreAdapter;


    @GetMapping("/books")
    @ResponseStatus(HttpStatus.OK)
    public LivreDTO searchBookByISBN(@RequestParam String isbn){
        return  livreAdapter.mapToDTO(livreService.searchBookByISBN(isbn));
    }
}
