package investigacion;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PublicacionTest {

    Publicacion p1 = new Publicacion("Tit1", Set.of("ORCID1","ORCID3"), 2020, "Revista1", List.of("Art1", "Art2","Art3"));
	Publicacion p2= new Publicacion("Tit2", Set.of("ORCID1"), 2021, "Revista2", List.of("Art11"));
    Publicacion p3 = new Publicacion("Tit3", Set.of("ORCID1","ORCID3"), 2022, "Revista1", List.of("Art13", "Art22","Art31", "Art43"));
    Publicacion p4 = new Publicacion("Tit4", Set.of("ORCID1"), 2019, "Revista3", List.of("Art3"));
    @Test
    void getTitulo() {
        assertEquals("Tit1", p1.getTitulo());
        assertEquals("Tit2", p2.getTitulo());
        assertEquals("Tit3", p3.getTitulo());
        assertEquals("Tit4", p4.getTitulo());
    }

    @Test
    void getAnyo() {
        assertEquals(2020, p1.getAnyo());
        assertEquals(2021, p2.getAnyo());
        assertEquals(2022, p3.getAnyo());
        assertEquals(2019, p4.getAnyo());
    }

    @Test
    void getRevista() {
        assertEquals("Revista1", p1.getRevista());
        assertEquals("Revista2", p2.getRevista());
        assertEquals("Revista1", p3.getRevista());
        assertEquals("Revista3", p4.getRevista());
    }

    @Test
    void esAutor() {
        assertTrue(p1.esAutor("ORCID1"));
        assertTrue(p1.esAutor("ORCID3"));
        assertFalse(p1.esAutor("ORCID2"));
        assertTrue(p2.esAutor("ORCID1"));
        assertFalse(p2.esAutor("ORCID3"));
        assertFalse(p2.esAutor("ORCID2"));
        assertTrue(p3.esAutor("ORCID1"));
        assertTrue(p3.esAutor("ORCID3"));
        assertFalse(p3.esAutor("ORCID2"));
        assertTrue(p4.esAutor("ORCID1"));
        assertFalse(p4.esAutor("ORCID3"));
        assertFalse(p4.esAutor("ORCID2"));
    }

    @Test
    void getNumCitas() {
        assertEquals(3, p1.getNumCitas());
        assertEquals(1, p2.getNumCitas());
        assertEquals(4, p3.getNumCitas());
        assertEquals(1, p4.getNumCitas());
    }
}