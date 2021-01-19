package fr.training.spring.library.exposition;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class DirecteurDTO {

    @JsonProperty("Prenom")
    @Size(min=2,max=25)
    @NotBlank
    private String prenom;
    @JsonProperty("Nom")
    @Size(min=2,max=25)
    @NotBlank
    private String nom;

    public DirecteurDTO(String prenom, String nom) {
        this.prenom = prenom;
        this.nom = nom;
    }

    public DirecteurDTO() {
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNom() {
        return nom;
    }

}
