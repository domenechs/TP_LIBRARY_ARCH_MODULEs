package fr.training.spring.library.batch.exportjob;

import fr.training.spring.library.domain.Type;

public class LibraryDTO {

    private String idLibrary;
    private int adresseNumero;
    private String adresseRue;
    private String adresseVille;
    private int adresseCodePostal;
    private String directeurPrenom;
    private String directeurNom;
    private Type type;

    public String getIdLibrary() {
        return idLibrary;
    }

    public void setIdLibrary(String idLibrary) {
        this.idLibrary = idLibrary;
    }

    public int getAdresseNumero() {
        return adresseNumero;
    }

    public void setAdresseNumero(int adresseNumero) {
        this.adresseNumero = adresseNumero;
    }

    public String getAdresseRue() {
        return adresseRue;
    }

    public void setAdresseRue(String adresseRue) {
        this.adresseRue = adresseRue;
    }

    public String getAdresseVille() {
        return adresseVille;
    }

    public void setAdresseVille(String adresseVille) {
        this.adresseVille = adresseVille;
    }

    public int getAdresseCodePostal() {
        return adresseCodePostal;
    }

    public void setAdresseCodePostal(int adresseCodePostal) {
        this.adresseCodePostal = adresseCodePostal;
    }

    public String getDirecteurPrenom() {
        return directeurPrenom;
    }

    public void setDirecteurPrenom(String directeurPrenom) {
        this.directeurPrenom = directeurPrenom;
    }

    public String getDirecteurNom() {
        return directeurNom;
    }

    public void setDirecteurNom(String directeurNom) {
        this.directeurNom = directeurNom;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
