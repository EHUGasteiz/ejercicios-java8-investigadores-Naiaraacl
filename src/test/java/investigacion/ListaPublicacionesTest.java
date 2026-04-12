package investigacion;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ListaPublicacionesTest {

    private final ListaPublicaciones listaPublicaciones = ListaPublicaciones.getListaPublicaciones();

    @BeforeEach
    void setUp() {
        listaPublicaciones.eliminarPublicaciones();
    }

    @Test
    void getPublicacionesInvestigador() {
        assertTrue(listaPublicaciones.getPublicacionesInvestigador("ORCID1").isEmpty());
        assertTrue(listaPublicaciones.getPublicacionesInvestigador("ORCID2").isEmpty());
        assertTrue(listaPublicaciones.getPublicacionesInvestigador("ORCID3").isEmpty());

        Publicacion pub1 = new Publicacion("Titulo1", Set.of("ORCID1", "ORCID2"), 2022, "Revista1", List.of());
        listaPublicaciones.addPublicacion(pub1);

        var pubOrcid1 = listaPublicaciones.getPublicacionesInvestigador("ORCID1");
        assertEquals(1, pubOrcid1.size());
        assertSame(pub1, pubOrcid1.get(0));
        var pubOrcid2 = listaPublicaciones.getPublicacionesInvestigador("ORCID2");
        assertEquals(1, pubOrcid2.size());
        assertSame(pub1, pubOrcid2.get(0));
        assertTrue(listaPublicaciones.getPublicacionesInvestigador("ORCID3").isEmpty());

        Publicacion pub2 = new Publicacion("Titulo1", Set.of("ORCID1", "ORCID3"), 2022, "Revista1", List.of());
        listaPublicaciones.addPublicacion(pub2);

        pubOrcid1 = listaPublicaciones.getPublicacionesInvestigador("ORCID1");
        var realPubOrcid1 = List.of(pub1, pub2);
        assertTrue(realPubOrcid1.containsAll(pubOrcid1));
        assertTrue(pubOrcid1.containsAll(realPubOrcid1));
        pubOrcid2 = listaPublicaciones.getPublicacionesInvestigador("ORCID2");
        assertEquals(1, pubOrcid2.size());
        assertSame(pub1, pubOrcid2.get(0));
        var pubOrcid3 = listaPublicaciones.getPublicacionesInvestigador("ORCID3");
        assertEquals(1, pubOrcid3.size());
        assertSame(pub2, pubOrcid3.get(0));

    }
}