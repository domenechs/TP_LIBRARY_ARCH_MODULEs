package fr.training.spring.library.domain;

import fr.training.spring.library.domain.ddd.DDD;

import java.util.List;

@DDD.Repository
public interface LibraryRepository {

    String save(Library library);
    Library findById(String id);
    List<Library> findAll();
    List<Library> findByType(Type type);
    List<Library> findByDirecteur_Prenom(String prenom);
    void deleteById(String id);

}
