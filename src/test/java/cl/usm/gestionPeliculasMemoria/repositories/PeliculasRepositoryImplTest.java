package cl.usm.gestionPeliculasMemoria.repositories;

import cl.usm.gestionPeliculasMemoria.entities.Pelicula;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PeliculasRepositoryImplTest {

    private PeliculasRepositoryImpl repository;

    @BeforeEach
    void setUp() {
        repository = new PeliculasRepositoryImpl();
    }

    @Test
    void debeInsertarPelicula() {

        Pelicula pelicula = new Pelicula();
        pelicula.setId("P1");
        pelicula.setTitulo("Matrix");

        Pelicula resultado = repository.insert(pelicula);

        assertEquals("P1", resultado.getId());
    }

    @Test
    void noDebePermitirIdNulo() {

        Pelicula pelicula = new Pelicula();

        assertThrows(
                IllegalArgumentException.class,
                () -> repository.insert(pelicula)
        );
    }

    @Test
    void noDebePermitirDuplicados() {

        Pelicula pelicula = new Pelicula();
        pelicula.setId("P1");

        repository.insert(pelicula);

        assertThrows(
                IllegalArgumentException.class,
                () -> repository.insert(pelicula)
        );
    }

    @Test
    void debeRetornarTodasLasPeliculas() {

        Pelicula pelicula = new Pelicula();
        pelicula.setId("P1");

        repository.insert(pelicula);

        List<Pelicula> resultado = repository.findAll();

        assertEquals(1, resultado.size());
    }

    @Test
    void debeBuscarPorId() {

        Pelicula pelicula = new Pelicula();
        pelicula.setId("P1");

        repository.insert(pelicula);

        Pelicula resultado = repository.findById("P1");

        assertNotNull(resultado);
    }

    @Test
    void debeRetornarNullSiNoExiste() {

        assertNull(repository.findById("XXX"));
    }

    @Test
    void debeRetornarNullSiIdEsNulo() {

        assertNull(repository.findById(null));
    }
}