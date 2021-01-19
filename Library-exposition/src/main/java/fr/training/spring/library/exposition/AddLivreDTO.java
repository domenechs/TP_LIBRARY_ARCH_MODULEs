package fr.training.spring.library.exposition;

import fr.training.spring.library.domain.GenreLitteraire;

public class AddLivreDTO {
    private String isbn;
    private GenreLitteraire genre;

    public String getIsbn() {
        return isbn;
    }

    public GenreLitteraire getGenre() {
        return genre;
    }
}
