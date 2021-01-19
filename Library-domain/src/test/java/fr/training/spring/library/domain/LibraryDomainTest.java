package fr.training.spring.library.domain;
import static org.assertj.core.api.Assertions.assertThat;


import org.junit.jupiter.api.Test;

import java.util.ArrayList;


public class LibraryDomainTest {

    @Test
    public void test_egalite_entiteLibrary(){
        //Given
        final Library NATIONAL_LIBRARY_MONTREUIL =
                new Library("0", Type.NATIONAL,
                        new Adresse(93, "Rue des Montreuil", 93100, "Montreuil"),
                        new Directeur("Romain", "NOEL"), new ArrayList<>());
        final Library SCHOOL_LIBRARY_PARIS = new Library("0", Type.SCOLAIRE,
                new Adresse(75, "Rue de Paris", 75008, "Paris"),
                new Directeur("Garfield", "LECHAT"), new ArrayList<>());
        //When
        //Then
        assertThat(NATIONAL_LIBRARY_MONTREUIL.equals(SCHOOL_LIBRARY_PARIS)).isTrue();
        assertThat(NATIONAL_LIBRARY_MONTREUIL.hashCode()).isEqualTo(SCHOOL_LIBRARY_PARIS.hashCode());

    }

    @Test
    public void test_non_egalite_entiteLibrary(){
        //Given
       final Library NATIONAL_LIBRARY_MONTREUIL =
                new Library("0", Type.NATIONAL,
                        new Adresse(93, "Rue des Montreuil", 93100, "Montreuil"),
                        new Directeur("Romain", "NOEL"), new ArrayList<>());
        final Library SCHOOL_LIBRARY_PARIS = new Library("1", Type.SCOLAIRE,
                new Adresse(75, "Rue de Paris", 75008, "Paris"),
                new Directeur("Garfield", "LECHAT"), new ArrayList<>());
        //When
        //Then
        assertThat(NATIONAL_LIBRARY_MONTREUIL.equals(SCHOOL_LIBRARY_PARIS)).isFalse();
        assertThat(NATIONAL_LIBRARY_MONTREUIL.hashCode()).isNotEqualTo(SCHOOL_LIBRARY_PARIS.hashCode());

    }

    @Test
    public void test_egalite_entityLivre(){
        //Given
        final Livre livre1 = new Livre(0, "SBN0001", "Le seigneur des anneaux", "Tolkien", 500, GenreLitteraire.FANTASTIQUE);
        final Livre livre2 = new Livre(0, "SBN0001", "Star Wars", "SPIELBERG", 800, GenreLitteraire.SCIENCE_FICTION);

        //When
        //Then
        assertThat(livre1.equals(livre2)).isTrue();
        assertThat(livre1.hashCode()).isEqualTo(livre2.hashCode());

    }

    @Test
    public void test_non_egalite_entitylivre(){
        //Given
        final Livre livre2 = new Livre(0, "SBN0001", "Star Wars", "SPIELBERG", 800, GenreLitteraire.SCIENCE_FICTION);
        final Livre livre3 = new Livre(0, "SBN0003", "Shining", "King", 400, GenreLitteraire.DRAME);
        //When
        //Then
        assertThat(livre3.equals(livre2)).isFalse();
        assertThat(livre3.hashCode()).isNotEqualTo(livre2.hashCode());
    }

    @Test
    public void test_egalite_valueobject(){
        //Given
        Adresse adresse1 = new Adresse(50,"rue",77500,"Chelles");
        Adresse adresse2 = new Adresse(50,"rue",77500,"Chelles");

        assertThat(adresse1.equals(adresse2)).isTrue();
        assertThat(adresse1.hashCode()).isEqualTo(adresse2.hashCode());

    }

    @Test
    public void test_non_egalite_valueobject(){
        //Given
        Adresse adresse1 = new Adresse(45,"rue",77500,"Chelles");
        Adresse adresse2 = new Adresse(50,"rue",77500,"Chelles");

        assertThat(adresse1.equals(adresse2)).isFalse();
        assertThat(adresse1.hashCode()).isNotEqualTo(adresse2.hashCode());

    }
}
