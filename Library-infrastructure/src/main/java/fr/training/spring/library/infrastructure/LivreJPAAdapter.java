package fr.training.spring.library.infrastructure;

import fr.training.spring.library.domain.Livre;
import org.springframework.stereotype.Component;

@Component
public class LivreJPAAdapter {

    public Livre mapToLivre(LivreJPA livreJPA){
        return new Livre(livreJPA.getId(), livreJPA.getIsbn(), livreJPA.getTitre(),
                livreJPA.getAuteur(), livreJPA.getNombreDePage(), livreJPA.getGenre());
    }

    public LivreJPA mapToJPA(Livre livre){
        return new LivreJPA(livre.getId(), livre.getIsbn(), livre.getTitre(),
                livre.getAuteur(), livre.getNombreDePage(), livre.getGenre());
    }
}
