package fr.training.spring.library.domain;


import fr.training.spring.library.domain.ddd.DDD;

import java.util.Objects;

@DDD.ValueObject
public class Directeur {

    private String prenom;

    private String nom;

    public Directeur(String prenom, String nom) {
        this.prenom = prenom;
        this.nom = nom;
    }

    public Directeur() {

    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Directeur directeur = (Directeur) o;
        return Objects.equals(prenom, directeur.prenom) && Objects.equals(nom, directeur.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prenom, nom);
    }
}
