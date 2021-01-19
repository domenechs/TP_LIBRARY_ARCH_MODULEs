package fr.training.spring.library.infrastructure;

import fr.training.spring.library.domain.Adresse;
import fr.training.spring.library.domain.Directeur;
import fr.training.spring.library.domain.Library;
import fr.training.spring.library.domain.Livre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LibraryJPAAdapter {

    @Autowired
    LivreJPAAdapter livreJPAAdapter;

    public Library mapToLibrary(LibraryJPA libraryJPA){
        Adresse adresse = new Adresse(libraryJPA.getNumero(), libraryJPA.getRue(),
                                        libraryJPA.getCodePostal(), libraryJPA.getVille());
        Directeur directeur = new Directeur((libraryJPA.getDirecteurPrenom()), libraryJPA.getNomDirecteur());

        return new Library(libraryJPA.getId(), libraryJPA.getType(),adresse,directeur,listeLivre(libraryJPA.getLivres()));
    }

    private List<Livre> listeLivre(List<LivreJPA> livresJPA) {
        List<Livre> livres = new ArrayList<>();
        for(LivreJPA livreJPA: livresJPA){
            if(livreJPA != null){
                livres.add(livreJPAAdapter.mapToLivre(livreJPA));
            }
        }
        return livres;
    }

    public LibraryJPA mapToJPA(Library library){
        return new LibraryJPA(library.getId(), library.getType(),library.getDirecteur().getPrenom(),
                library.getDirecteur().getNom(),library.getAdresse().getNumero(),library.getAdresse().getRue(),
                library.getAdresse().getVille(),library.getAdresse().getCodePostal(),listeLivreJPA(library.getLivres()));

    }

    private List<LivreJPA> listeLivreJPA(List<Livre> livres) {
        List<LivreJPA> livresJPA = new ArrayList<>();
        for(Livre livre: livres){
            if(livre != null){
                livresJPA.add(livreJPAAdapter.mapToJPA(livre));
            }
        }
        return livresJPA;
    }

    public List<Library> mapToLibrary(List<LibraryJPA> librariesJPA){
        List<Library> libraries = new ArrayList<>();
        for(LibraryJPA libraryJPA:librariesJPA){
            libraries.add(mapToLibrary(libraryJPA));
        }
        return libraries;
    }

}
