package fr.training.spring.library.infrastructure;

import fr.training.spring.library.domain.GenreLitteraire;
import fr.training.spring.library.domain.Livre;

import javax.persistence.*;

@Entity
@Table(name = "LIVRE")
public class LivreJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="IDENTIFIANT")
    private long id;
    @Column(name="ISBN")
    private String isbn;
    @Column(name="TITRE")
    private String titre;
    @Column(name="AUTEUR")
    private String auteur;
    @Column(name="NOMBRE_DE_PAGE")
    private int nombreDePage;
    @Enumerated(EnumType.STRING)
    @Column(name="GENRE")
    private GenreLitteraire genre;

    public LivreJPA() {
    }

    public LivreJPA(long id, String isbn, String titre, String auteur, int nombreDePage, GenreLitteraire genre) {
        this.id = id;
        this.isbn = isbn;
        this.titre = titre;
        this.auteur = auteur;
        this.nombreDePage = nombreDePage;
        this.genre = genre;
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

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public int getNombreDePage() {
        return nombreDePage;
    }

    public void setNombreDePage(int nombreDePage) {
        this.nombreDePage = nombreDePage;
    }

    public GenreLitteraire getGenre() {
        return genre;
    }

    public void setGenre(GenreLitteraire genre) {
        this.genre = genre;
    }
}
