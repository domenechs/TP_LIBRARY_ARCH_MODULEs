package fr.training.spring.library.infrastructure;

import fr.training.spring.library.domain.Library;
import fr.training.spring.library.domain.LibraryRepository;
import fr.training.spring.library.domain.Type;
import fr.training.spring.library.domain.common.exception.ErrorCode;
import fr.training.spring.library.domain.common.exception.NotFoundLibraryException;
import fr.training.spring.library.domain.ddd.DDD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@DDD.RepositoryImpl
@Repository
public class LibraryRepositoryImpl implements LibraryRepository {

    @Autowired
    LibraryDao libraryDao;
    @Autowired
    LibraryJPAAdapter libraryJPAAdapter;

    @Override
    public String save(Library library) {
        LibraryJPA libraryJPA = libraryDao.save(libraryJPAAdapter.mapToJPA(library));
        return libraryJPA.getId();
    }

    @Override
    public Library findById(String id) {
        return libraryJPAAdapter.mapToLibrary(libraryDao.findById(id)
                .orElseThrow(()-> new NotFoundLibraryException("Library n'existe pas!!!", ErrorCode.LIBRARY_NOT_FOUND)));
    }

    @Override
    public List<Library> findAll() {
        return libraryJPAAdapter.mapToLibrary(libraryDao.findAll());
    }

    @Override
    public List<Library> findByType(Type type) {
        return libraryJPAAdapter.mapToLibrary(libraryDao.findByType(type));

    }

    @Override
    public List<Library> findByDirecteur_Prenom(String prenom) {
        return libraryJPAAdapter.mapToLibrary(libraryDao.findByDirecteurPrenom(prenom));

    }

    @Override
    public void deleteById(String id) {
        libraryDao.deleteById(id);
    }
}
