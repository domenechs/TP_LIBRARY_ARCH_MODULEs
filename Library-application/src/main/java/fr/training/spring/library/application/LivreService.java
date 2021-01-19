package fr.training.spring.library.application;

import fr.training.spring.library.domain.Livre;
import fr.training.spring.library.domain.LivreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LivreService {

    @Autowired
    private LivreRepository livreRepository;

    public Livre searchBookByISBN(String isbn){
        return livreRepository.search(isbn);
    }
}
