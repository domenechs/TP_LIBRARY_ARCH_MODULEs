package fr.training.spring.library.exposition;

import fr.training.spring.library.domain.Livre;
import org.springframework.stereotype.Component;

@Component
public class LivreAdapter {

    public Livre mapToLivre(LivreDTO livreDTO){
        return new Livre(livreDTO.getId(), livreDTO.getIsbn(), livreDTO.getTitre(), livreDTO.getAuteur(),
                livreDTO.getNombreDePage(), livreDTO.getGenre());
    }

    public LivreDTO mapToDTO(Livre livre){
        return new LivreDTO(livre.getId(), livre.getIsbn(), livre.getTitre(), livre.getAuteur(), livre.getNombreDePage(),
                livre.getGenre());
    }
}
