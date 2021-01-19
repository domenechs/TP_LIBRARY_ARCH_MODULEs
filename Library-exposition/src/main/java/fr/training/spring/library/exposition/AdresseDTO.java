package fr.training.spring.library.exposition;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class AdresseDTO {

    @JsonProperty("Numero de rue")
    @Positive(message = "Le numéro doit être supérieur à 0")
    private int numero;
    @NotNull
    @NotBlank
    @JsonProperty("Rue")
    private String rue;
    @JsonProperty("Code Postal")
    @Positive
    private int codePostal;
    @JsonProperty("Ville")
    @NotNull
    @NotBlank
    private String ville;

    public AdresseDTO(int numero, String rue, int codePostal, String ville) {
        this.numero = numero;
        this.rue = rue;
        this.codePostal = codePostal;
        this.ville = ville;
    }

    public int getNumero() {
        return numero;
    }

    public String getRue() {
        return rue;
    }

    public int getCodePostal() {
        return codePostal;
    }

    public String getVille() {
        return ville;
    }

}
