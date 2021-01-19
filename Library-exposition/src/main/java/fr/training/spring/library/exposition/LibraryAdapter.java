package fr.training.spring.library.exposition;

import fr.training.spring.library.domain.Adresse;
import fr.training.spring.library.domain.Directeur;
import fr.training.spring.library.domain.Library;
import fr.training.spring.library.domain.Livre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LibraryAdapter {

    @Autowired
    private LivreAdapter livreAdapter;

    public Library mapToLibrary(LibraryDTO libraryDTO){
        Adresse adresse = new Adresse(libraryDTO.getAdresseDTO().getNumero(),libraryDTO.getAdresseDTO().getRue(),
                libraryDTO.getAdresseDTO().getCodePostal(),libraryDTO.getAdresseDTO().getVille());
        Directeur directeur = new Directeur(libraryDTO.getDirecteurDTO().getPrenom(),libraryDTO.getDirecteurDTO().getNom());
        return new Library(libraryDTO.getId(),libraryDTO.getType(),adresse,directeur,listLivre(libraryDTO.getLivresDTO()));

    }

    private List<Livre> listLivre(List<LivreDTO> livresDTO) {
        List<Livre> livres = new ArrayList<>();
        for(LivreDTO dto: livresDTO){
            if (dto != null) {
                livres.add(livreAdapter.mapToLivre(dto));
            }
        }
        return livres;
    }

    public LibraryDTO mapToDTO(Library library){
        AdresseDTO adresseDTO = new AdresseDTO(library.getAdresse().getNumero(),library.getAdresse().getRue(),
                                library.getAdresse().getCodePostal(),library.getAdresse().getVille());
        DirecteurDTO directeurDTO = new DirecteurDTO(library.getDirecteur().getPrenom(),library.getDirecteur().getNom());
        return new LibraryDTO(library.getId(), library.getType(),adresseDTO,directeurDTO,listeLivreDTO(library.getLivres()));
    }

    private List<LivreDTO> listeLivreDTO(List<Livre> livres) {
        List<LivreDTO> livresDTO = new ArrayList<>();
        for(Livre livre: livres){
            if (livre != null) {
                livresDTO.add(livreAdapter.mapToDTO(livre));
            }
        }
        return livresDTO;
    }

    public List<LibraryDTO> mapToDTO(List<Library> listLibrary){
        List<LibraryDTO> listDTO = new ArrayList<>();
        for(Library library: listLibrary){
            listDTO.add(mapToDTO(library));
        }
        return listDTO;
    }
}
