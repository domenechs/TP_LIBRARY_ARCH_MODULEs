package fr.training.spring.library.domain;

public interface LivreRepository {
    Livre search(String isbn);
}
