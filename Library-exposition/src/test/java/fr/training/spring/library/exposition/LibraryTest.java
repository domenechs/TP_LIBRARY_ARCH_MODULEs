package fr.training.spring.library.exposition;

import static org.assertj.core.api.Assertions.assertThat;

import fr.training.spring.library.domain.*;
import fr.training.spring.library.infrastructure.LibraryDao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.ArrayList;
import java.util.Arrays;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LibraryTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private LibraryDao libraryDao;

    public static final LivreDTO livre1 = new LivreDTO(0, "SBN0001", "Le seigneur des anneaux", "Tolkien", 500, GenreLitteraire.FANTASTIQUE);
    public static final LivreDTO livre2 = new LivreDTO(0, "SBN0002", "Star Wars", "SPIELBERG", 800, GenreLitteraire.SCIENCE_FICTION);
    public static final LivreDTO livre3 = new LivreDTO(0, "SBN0003", "Shining", "King", 400, GenreLitteraire.DRAME);


    public static final LibraryDTO NATIONAL_LIBRARY_MONTREUIL =
            new LibraryDTO("0", Type.NATIONAL,
                    new AdresseDTO(93, "Rue des Montreuil", 93100, "Montreuil"),
                    new DirecteurDTO("Romain", "NOEL"), new ArrayList<>(Arrays.asList(livre1, livre2)));
    public static final LibraryDTO SCHOOL_LIBRARY_PARIS = new LibraryDTO("0", Type.SCOLAIRE,
            new AdresseDTO(75, "Rue de Paris", 75008, "Paris"),
            new DirecteurDTO("Garfield", "LECHAT"), new ArrayList<>(Arrays.asList(livre2, livre3)));
    public static final LibraryDTO PUBLIC_LIBRARY_VINCENNES = new LibraryDTO("0", Type.PUBLIQUE,
            new AdresseDTO(94, "Rue de Vincennes", 94200, "Vincennes"),
            new DirecteurDTO("Garfield", "LECHAT"), new ArrayList<>(Arrays.asList(livre3, livre1)));

    @BeforeEach
    public void delete_all() {
        libraryDao.deleteAll();
    }

    @Test
    public void test_Library_Found() {
        ResponseEntity<String> newLibrary = restTemplate.postForEntity("/api/libraries/create", PUBLIC_LIBRARY_VINCENNES, String.class);
        System.out.println("id: "+newLibrary.getBody());
        final ResponseEntity<LibraryDTO> response = restTemplate.getForEntity("/api/detail/" + newLibrary.getBody(), LibraryDTO.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getId()).isEqualTo(newLibrary.getBody());
        assertThat(response.getBody().getDirecteurDTO().getNom()).isEqualTo("LECHAT");
//        assertThat(response.getBody().getType()).isEqualTo("ASSOCIATIVE");
//        assertThat(response.getBody().getLivres().size()).isEqualTo(2);
//        assertThat(response.getBody().getLivres().get(0).getAuteur()).isEqualTo("Tolkien");

    }

    @Test
    public void test_Library_NotFound() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/detail/8", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).contains("ERR_0001");
    }


    @Test
    @DisplayName("doit retourner une liste vide car aucune insertions avant")
    public void test_read_all_empty() {
        ResponseEntity<LibraryDTO[]> response = restTemplate.getForEntity("/api/listall", LibraryDTO[].class);
        assertThat(response.getBody()).isEmpty();
    }

    @Test
    @DisplayName("doit retourner une liste de 3 librarys")
    public void test_read_all() {
        restTemplate.postForObject("/api/libraries/create", NATIONAL_LIBRARY_MONTREUIL, String.class);
        restTemplate.postForObject("/api/libraries/create", SCHOOL_LIBRARY_PARIS, String.class);
        restTemplate.postForObject("/api/libraries/create", PUBLIC_LIBRARY_VINCENNES, String.class);
        ResponseEntity<LibraryDTO[]> response = restTemplate.getForEntity("/api/listall", LibraryDTO[].class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull().hasSize(3);
    }

    @Test
    @DisplayName("suppression d'une library via son ID")
    public void test_Delete_By_ID() {

        restTemplate.delete("/api/deletelibrary/2");

        ResponseEntity<String> response = restTemplate.getForEntity("/api/detail/2", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

    }

    @Test
    public void create_library() {
        //Given
        //When

        String id = restTemplate.postForObject("/api/libraries/create", NATIONAL_LIBRARY_MONTREUIL, String.class);

        ResponseEntity<LibraryDTO> response = restTemplate.getForEntity(("/api/detail/" + id), LibraryDTO.class);
        //Then
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody().getDirecteurDTO().getNom()).isEqualTo("NOEL");
        assertThat(response.getBody().getLivresDTO().size()).isEqualTo(2);

    }


    @Test
    public void update_library() {
        //Given
        String id = restTemplate.postForObject("/api/libraries/create", NATIONAL_LIBRARY_MONTREUIL, String.class);

        ResponseEntity<LibraryDTO> libraryResponse = restTemplate.getForEntity(("/api/detail/" + id), LibraryDTO.class);
        libraryResponse.getBody().setType(Type.PUBLIQUE);
        //When
        restTemplate.put("/api/update",libraryResponse.getBody(),LibraryDTO.class);
        ResponseEntity<LibraryDTO> libraryUpdate = restTemplate.getForEntity(("/api/detail/" + id), LibraryDTO.class);
        //Then
        assertThat(libraryUpdate.getBody().getType()).isEqualTo(Type.PUBLIQUE);

    }

}
