package fr.training.spring.library.domain;

import fr.training.spring.library.domain.common.exception.ErrorCode;
import fr.training.spring.library.domain.common.exception.ValidationException;
import fr.training.spring.library.domain.ddd.DDD;
import org.springframework.util.StringUtils;

import java.util.Objects;

@DDD.Entity
public class Livre {

    private long id;
    private String isbn;
    private String titre;
    private String auteur;
    private int nombreDePage;
    private GenreLitteraire genre;


    public Livre(long id, String isbn, String titre, String auteur, int nombreDePage, GenreLitteraire genre) {
        this.id = id;
        this.isbn = isbn;
        this.titre = titre;
        this.auteur = auteur;
        this.nombreDePage = nombreDePage;
        this.genre = genre;
        validate();
    }

    public void validate(){
        if(this == null){
            throw new ValidationException("Le Livre est obligatoire",ErrorCode.LIBRARY_MUST_HAVE_BOOK);
        }
        if(StringUtils.isEmpty(isbn)){
            throw new ValidationException("Numero ISBN est obligatoire",ErrorCode.ISBN_VALIDATION_ERROR);
        }
//        StringUtils.isEmpty(titre) || StringUtils.isEmpty(auteur) ||
//            nombreDePage > 0){
//            throw new ValidationException("")
//        }
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitre() {
        return titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public int getNombreDePage() {
        return nombreDePage;
    }

    public GenreLitteraire getGenre() {
        return genre;
    }

    public void setGenre(GenreLitteraire genre) {
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Livre livre = (Livre) o;
        return Objects.equals(isbn, livre.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }
}
