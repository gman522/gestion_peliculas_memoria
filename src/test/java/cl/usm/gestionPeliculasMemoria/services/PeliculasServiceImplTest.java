package cl.usm.gestionPeliculasMemoria.services;

import cl.usm.gestionPeliculasMemoria.entities.Pelicula;
import cl.usm.gestionPeliculasMemoria.repositories.PeliculasRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PeliculasServiceImplTest {

    @Mock
    private PeliculasRepository peliculasRepository;

    @InjectMocks
    private PeliculasServiceImpl peliculasService;

    @Test
    void debeCrearPelicula() {

        Pelicula pelicula = new Pelicula();
        pelicula.setId("P1");

        when(peliculasRepository.insert(any(Pelicula.class)))
                .thenReturn(pelicula);

        Pelicula resultado = peliculasService.createPelicula(pelicula);

        assertNotNull(resultado);
        assertNotNull(resultado.getTokenDescarga());

        verify(peliculasRepository).insert(any(Pelicula.class));
    }

    @Test
    void debeRetornarNullCuandoFallaInsert() {

        Pelicula pelicula = new Pelicula();
        pelicula.setId("P1");

        when(peliculasRepository.insert(any(Pelicula.class)))
                .thenThrow(new RuntimeException());

        Pelicula resultado = peliculasService.createPelicula(pelicula);

        assertNull(resultado);
    }

    @Test
    void debeRetornarTodasLasPeliculas() {

        Pelicula p1 = new Pelicula();
        p1.setId("P1");

        Pelicula p2 = new Pelicula();
        p2.setId("P2");

        when(peliculasRepository.findAll())
                .thenReturn(Arrays.asList(p1, p2));

        List<Pelicula> resultado = peliculasService.getAll();

        assertEquals(2, resultado.size());
    }

    @Test
    void debeBuscarPorId() {

        Pelicula pelicula = new Pelicula();
        pelicula.setId("P1");

        when(peliculasRepository.findById("P1"))
                .thenReturn(pelicula);

        Pelicula resultado = peliculasService.findById("P1");

        assertNotNull(resultado);
        assertEquals("P1", resultado.getId());
    }

    @Test
    void debeFiltrarPorTitulo() {

        Pelicula p1 = new Pelicula();
        p1.setId("P1");
        p1.setTitulo("Matrix");

        Pelicula p2 = new Pelicula();
        p2.setId("P2");
        p2.setTitulo("Titanic");

        when(peliculasRepository.findAll())
                .thenReturn(Arrays.asList(p1, p2));

        List<Pelicula> resultado = peliculasService.filter("matrix");

        assertEquals(1, resultado.size());
    }

    @Test
    void debeFiltrarPorId() {

        Pelicula p1 = new Pelicula();
        p1.setId("ABC123");
        p1.setTitulo("Matrix");

        when(peliculasRepository.findAll())
                .thenReturn(List.of(p1));

        List<Pelicula> resultado = peliculasService.filter("abc");

        assertEquals(1, resultado.size());
    }
}