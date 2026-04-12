package investigacion;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CatalogoInvestigadoresTest {
    private final CatalogoInvestigadores catInvestigadores = CatalogoInvestigadores.getCatalogoInvestigadores();

    private final Investigador r1 = new Investigador("ORCID1", "Researcher1", "Dept1", LocalDate.of(1967, 7, 18));
    private final Investigador r2 = new Investigador("ORCID2", "Researcher2", "Dept2", LocalDate.of(1957, 6, 8));
    private final Investigador r3 = new Investigador("ORCID3", "Researcher3", "Dept2", LocalDate.of(1957, 6, 8));

    private final ListaPublicaciones listaPublicaciones = ListaPublicaciones.getListaPublicaciones();


    @BeforeEach
    void setUp() {
        catInvestigadores.eliminaInvestigadores();
        catInvestigadores.addInvestigador(r1);
        catInvestigadores.addInvestigador(r2);
        catInvestigadores.addInvestigador(r3);
        listaPublicaciones.eliminarPublicaciones();
        listaPublicaciones.addPublicacion(new Publicacion("Tit1", Set.of("ORCID1", "ORCID3"), 2020, "Revista1", List.of("Art1", "Art2", "Art3")));
        listaPublicaciones.addPublicacion(new Publicacion("Tit2", Set.of("ORCID1"), 2021, "Revista2", List.of("Art11")));
        listaPublicaciones.addPublicacion(new Publicacion("Tit3", Set.of("ORCID1", "ORCID3"), 2022, "Revista1", List.of("Art13", "Art22", "Art31", "Art43")));
        listaPublicaciones.addPublicacion(new Publicacion("Tit4", Set.of("ORCID1"), 2019, "Revista3", List.of("Art3")));
    }

    @Test
    void getListaInvestigadores() {
        List<Investigador> listaReal = List.of(r1, r2, r3);
        var listaInvestigadores = catInvestigadores.getListaInvestigadores();
        assertTrue(listaReal.containsAll(listaInvestigadores));
        assertTrue(listaInvestigadores.containsAll(listaReal));

        var r4 = new Investigador("ORCID4", "Researcher4","Dept4", LocalDate.of(1978, 1, 23));
        catInvestigadores.addInvestigador(r4);

        listaReal = List.of(r1, r2, r3, r4);
        listaInvestigadores = catInvestigadores.getListaInvestigadores();

        assertTrue(listaReal.containsAll(listaInvestigadores));
        assertTrue(listaInvestigadores.containsAll(listaReal));
    }

    @Test
    void getListaInvestigadoresOrdenadaPorPublicaciones() {
        var listaReal = List.of(r1, r3, r2);
        var listaInvestigadores = catInvestigadores.getListaInvestigadoresOrdenadaPorPublicaciones();
        assertIterableEquals(listaReal, listaInvestigadores);
    }

    @Test
    void getMediaCitasPorDepartamento() {
        var citasPorDep = catInvestigadores.getMediaCitasPorDepartamento();
        var citasPorDepReales = Map.of("Dept1", 9.0, "Dept2", 3.5);

        citasPorDepReales.forEach((dep,citas) -> assertEquals(citas, citasPorDep.get(dep)));
    }

    @Test
    void getInvestigadoresPorDepYHIndex() {
        var investigadoresPorDepHIndex = catInvestigadores.getInvestigadoresPorDepYHIndex();
        var invDepHIndexReales = Map.of("Dept1", Map.of(2, List.of("Researcher1")),
                                                                             "Dept2", Map.of(2, List.of("Researcher3"),
                                                                                                     0, List.of("Researcher2")));
        invDepHIndexReales.forEach(
                (dep,hIndex) -> hIndex.forEach(
                        (index, autores) -> assertIterableEquals(autores, investigadoresPorDepHIndex.get(dep).get(index))));
    }

    @Test
    void getInvestigadoresOrdenadosPorDepYHIndex() {
        var listaInvestigadoresReales = List.of(r1, r3, r2);
        var listaInvestigadores = catInvestigadores.getInvestigadoresOrdenadosPorDepYHIndex();

        assertIterableEquals(listaInvestigadoresReales, listaInvestigadores);
    }
}