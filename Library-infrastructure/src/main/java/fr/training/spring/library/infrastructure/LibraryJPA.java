package fr.training.spring.library.infrastructure;

import fr.training.spring.library.domain.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "LIBRARY")
public class LibraryJPA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDENTIFIANT")
    private String id;
    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE")
    private Type type;
    @Column(name = "PRENOM_DIRECTEUR")
    private String directeurPrenom;
    @Column(name = "NOM_DIRECTEUR")
    private String nomDirecteur;
    @Column(name = "NUMERO")
    private int numero;
    @Column(name = "RUE")
    private String rue;
    @Column(name = "VILLE")
    private String ville;
    @Column(name = "CODE_POSTAL")
    private int codePostal;
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true,fetch=FetchType.LAZY)
    @JoinColumn(name = "LIBRARY_ID",referencedColumnName = "IDENTIFIANT")
    private List<LivreJPA> livres;

    public LibraryJPA() {
    }

    public LibraryJPA(String id, Type type, String prenomDirecteur, String nomDirecteur, int numero, String rue, String ville, int codePostal, List<LivreJPA> livres) {
        this.id = id;
        this.type = type;
        this.directeurPrenom = prenomDirecteur;
        this.nomDirecteur = nomDirecteur;
        this.numero = numero;
        this.rue = rue;
        this.ville = ville;
        this.codePostal = codePostal;
        this.livres = livres;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getDirecteurPrenom() {
        return directeurPrenom;
    }

    public void setDirecteurPrenom(String directeurPrenom) {
        this.directeurPrenom = directeurPrenom;
    }

    public String getNomDirecteur() {
        return nomDirecteur;
    }

    public void setNomDirecteur(String nomDirecteur) {
        this.nomDirecteur = nomDirecteur;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public int getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(int codePostal) {
        this.codePostal = codePostal;
    }

    public List<LivreJPA> getLivres() {
        return livres;
    }

    public void setLivres(List<LivreJPA> livres) {
        this.livres = livres;
    }
}
