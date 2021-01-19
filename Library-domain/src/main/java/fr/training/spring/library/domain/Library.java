package fr.training.spring.library.domain;


import fr.training.spring.library.domain.ddd.DDD;

import java.util.List;
import java.util.Objects;

@DDD.Entity
@DDD.AggregateRoot
public class Library {

    private String id;

    private Type type;

    private Adresse adresse;

    private Directeur directeur;

    private List<Livre> livres;


    public Library(String id, Type type, Adresse adresse, Directeur directeur, List<Livre> livres) {
        this.id = id;
        this.type = type;
        this.adresse = adresse;
        this.directeur = directeur;
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

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public Directeur getDirecteur() {
        return directeur;
    }

    public void setDirecteur(Directeur directeur) {
        this.directeur = directeur;
    }

    public List<Livre> getLivres() {
        return livres;
    }

    public void setLivres(List<Livre> livres) {
        this.livres = livres;
    }

    public void addLivre(Livre livre){
        livres.add(livre);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Library library = (Library) o;
        return Objects.equals(id, library.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
