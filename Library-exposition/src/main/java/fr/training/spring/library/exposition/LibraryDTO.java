package fr.training.spring.library.exposition;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.training.spring.library.domain.Type;
import fr.training.spring.library.domain.common.exception.ErrorCode;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;


public class LibraryDTO {
// Library
    @JsonProperty("Identifiant library")
    private String id;
    @JsonProperty("Type")
    private Type type;
//Adresse
    @JsonProperty("adresse library")
    @NotNull(message = "L'adresse est obligatoire!! Code erreur = "+ErrorCode.ADRESSE_VALIDATION_ERROR)
    @Valid
    private AdresseDTO adresseDTO;
//Directeur
    @JsonProperty("Directeur")
    @NotNull(message = "Le directeur est obligatoire!! Code erreur = "+ErrorCode.DIRECTEUR_VALIDATION_ERROR)
    @Valid
    private DirecteurDTO directeurDTO;
    @JsonProperty("Livre")
    private List<LivreDTO> livresDTO;

    public LibraryDTO(String id, Type type, AdresseDTO adresseDTO, DirecteurDTO directeurDTO, List<LivreDTO> livresDTO) {
        this.id = id;
        this.type = type;
        this.adresseDTO = adresseDTO;
        this.directeurDTO = directeurDTO;
        this.livresDTO = livresDTO;
    }

    public LibraryDTO() {
    }

    public String getId() {
        return id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public AdresseDTO getAdresseDTO() {
        return adresseDTO;
    }

    public DirecteurDTO getDirecteurDTO() {
        return directeurDTO;
    }

    public List<LivreDTO> getLivresDTO() {
        return livresDTO;
    }

}
