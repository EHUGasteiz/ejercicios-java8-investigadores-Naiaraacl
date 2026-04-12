package investigacion;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class InvestigadorTest {

    private final Investigador p1 = new Investigador("ORCID1", "Researcher1", "Dept1", LocalDate.of(1967, 7, 18));
    private final Investigador p2 = new Investigador("ORCID2", "Researcher2", "Dept2", LocalDate.of(1957, 6, 8));
    private final Investigador p3 = new Investigador("ORCID3", "Researcher3", "Dept2", LocalDate.of(1957, 6, 8));

    private final ListaPublicaciones listaPublicaciones = ListaPublicaciones.getListaPublicaciones();


    @BeforeEach
    void setUp() {
        listaPublicaciones.eliminarPublicaciones();
        listaPublicaciones.addPublicacion(new Publicacion("Tit1", Set.of("ORCID1", "ORCID3"), 2020, "Revista1", List.of("Art1", "Art2", "Art3")));
        listaPublicaciones.addPublicacion(new Publicacion("Tit2", Set.of("ORCID1"), 2021, "Revista2", List.of("Art11")));
        listaPublicaciones.addPublicacion(new Publicacion("Tit3", Set.of("ORCID1", "ORCID3"), 2022, "Revista1", List.of("Art13", "Art22", "Art31", "Art43")));
        listaPublicaciones.addPublicacion(new Publicacion("Tit4", Set.of("ORCID1"), 2019, "Revista3", List.of("Art3")));
    }

    @Test
    void getId() {
        assertEquals("ORCID1", p1.getId());
        assertEquals("ORCID2", p2.getId());
        assertEquals("ORCID3", p3.getId());
    }

    @Test
    void getNombre() {
        assertEquals("Researcher1", p1.getNombre());
        assertEquals("Researcher2", p2.getNombre());
        assertEquals("Researcher3", p3.getNombre());
    }

    @Test
    void getFechaNacimiento() {
        assertEquals(LocalDate.of(1967, 7, 18), p1.getFechaNacimiento());
        assertEquals(LocalDate.of(1957, 6, 8), p2.getFechaNacimiento());
        assertEquals(LocalDate.of(1957, 6, 8), p3.getFechaNacimiento());
    }

    @Test
    void getDepartamento() {
        assertEquals("Dept1", p1.getDepartamento());
        assertEquals("Dept2", p2.getDepartamento());
        assertEquals("Dept2", p3.getDepartamento());
    }

    @Test
    void getHIndex() {
        assertEquals(2, p1.getHIndex());
        assertEquals(0, p2.getHIndex());
        assertEquals(2, p3.getHIndex());

        listaPublicaciones.addPublicacion(new Publicacion("Tit4", Set.of("ORCID3"), 2023, "Revista4", List.of("Artic45", "Artic46", "Artic53")));

        assertEquals(2, p1.getHIndex());
        assertEquals(0, p2.getHIndex());
        assertEquals(3, p3.getHIndex());
    }

    @Test
    void getNumPublicacionesConAlMenosCitas() {
        assertEquals(4, p1.getNumPublicacionesConAlMenosCitas(1));
        assertEquals(2, p1.getNumPublicacionesConAlMenosCitas(2));
        assertEquals(2, p1.getNumPublicacionesConAlMenosCitas(3));
        assertEquals(1, p1.getNumPublicacionesConAlMenosCitas(4));
        assertEquals(0, p1.getNumPublicacionesConAlMenosCitas(5));
        assertEquals(0, p2.getNumPublicacionesConAlMenosCitas(1));
        assertEquals(2, p3.getNumPublicacionesConAlMenosCitas(1));
        assertEquals(2, p3.getNumPublicacionesConAlMenosCitas(2));
        assertEquals(2, p3.getNumPublicacionesConAlMenosCitas(3));
        assertEquals(1, p3.getNumPublicacionesConAlMenosCitas(4));
        assertEquals(0, p3.getNumPublicacionesConAlMenosCitas(5));
    }

    @Test
    void getNumeroPublicaciones() {
        assertEquals(4, p1.getNumeroPublicaciones());
        assertEquals(0, p2.getNumeroPublicaciones());
        assertEquals(2, p3.getNumeroPublicaciones());
    }

    @Test
    void getNumCitasTotales() {
        assertEquals(9, p1.getNumCitasTotales());
        assertEquals(0, p2.getNumCitasTotales());
        assertEquals(7, p3.getNumCitasTotales());
    }

    @Test
    void getAge() {
        assertEquals(Period.between(p1.getFechaNacimiento(), LocalDate.now()).getYears(), p1.getAge());
        assertEquals(Period.between(p2.getFechaNacimiento(), LocalDate.now()).getYears(), p2.getAge());
        assertEquals(Period.between(p3.getFechaNacimiento(),LocalDate.now()).getYears(), p3.getAge());
    }
}