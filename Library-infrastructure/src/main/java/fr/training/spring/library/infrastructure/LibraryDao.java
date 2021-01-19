package fr.training.spring.library.infrastructure;

import fr.training.spring.library.domain.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibraryDao extends JpaRepository<LibraryJPA,String> {
    List<LibraryJPA> findByType(Type type);
    List<LibraryJPA> findByDirecteurPrenom(String prenom);
}
