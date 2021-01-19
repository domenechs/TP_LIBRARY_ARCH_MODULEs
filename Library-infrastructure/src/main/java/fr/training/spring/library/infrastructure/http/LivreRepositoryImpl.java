package fr.training.spring.library.infrastructure.http;

import fr.training.spring.library.domain.Livre;
import fr.training.spring.library.domain.LivreRepository;
import fr.training.spring.library.domain.common.exception.ErrorCode;
import fr.training.spring.library.domain.common.exception.NotFoundLibraryException;
import fr.training.spring.library.domain.common.exception.OpenLibraryTechnicalException;
import fr.training.spring.library.infrastructure.http.dto.AuthorInfo;
import fr.training.spring.library.infrastructure.http.dto.BookInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class LivreRepositoryImpl implements LivreRepository {

    @Autowired
    private RestTemplate restTemplate;

    public Livre search(String isbn) {
        BookInfo bookInfo;
        AuthorInfo authorInfo;

        try {
            ResponseEntity<BookInfo> response = restTemplate.getForEntity("/isbn/" + isbn + ".json", BookInfo.class);
            bookInfo = response.getBody();
            authorInfo = searchAuthor(bookInfo.getAuthors().get(0).getKey());

        } catch (HttpClientErrorException | HttpServerErrorException e) {
            if (HttpStatus.NOT_FOUND.equals(e.getStatusCode())) {
                throw new NotFoundLibraryException("Aucun livre correspondant à ce numero ISBN", ErrorCode.LIBRARY_NOT_FOUND);
            }
            throw new OpenLibraryTechnicalException(e);
        }



        return new Livre(0, isbn, bookInfo.getTitle(), authorInfo.getName(), bookInfo.getNumber_of_pages(), null);
    }

    private AuthorInfo searchAuthor(String key) {
       AuthorInfo authorInfo;
        try {
            ResponseEntity<AuthorInfo> response = restTemplate.getForEntity(key+ ".json", AuthorInfo.class);
            authorInfo = response.getBody();

        } catch (HttpClientErrorException | HttpServerErrorException e) {
            if (HttpStatus.NOT_FOUND.equals(e.getStatusCode())) {
                throw new NotFoundLibraryException("Pas d'auteur trouvé", ErrorCode.AUTHOR_NOT_FOUND);
            }
            throw new OpenLibraryTechnicalException(e);
        }
        return authorInfo;
    }
}
