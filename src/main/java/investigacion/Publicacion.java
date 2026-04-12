package investigacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Publicacion {
    private final String titulo;
    private final Set<String> listaAutores;
    private final int anyo;
    private final String revista;
    private final List<String> articulosQueLoCitan;


    /**
     * Crea una nueva instancia de Publicacion
     * @param pTitulo - Título de la publicación
     * @param pListaAutores - Conjunto de autores (ORCID)
     * @param pAnyo - Año de publicación
     * @param pRevista - Revista en la que fue publicada
     * @param pArticulosQueLoCitan - Artículos que referencian o citan la publicación
     */
    public Publicacion(String pTitulo, Set<String> pListaAutores, int pAnyo, String pRevista,
                       List<String> pArticulosQueLoCitan) {
        super();
        titulo = pTitulo;
        listaAutores = Set.copyOf(pListaAutores);
        anyo = pAnyo;
        revista = pRevista;
        articulosQueLoCitan = new ArrayList<>(pArticulosQueLoCitan);
    }


    /**
     * Devuelve el título de la publicación
     * @return el título de la publicación
     */
    public String getTitulo() {
        return titulo;
    }


    /**
     * Devuelve el año de publicación
     * @return el año de publicación
     */
    public int getAnyo() {
        return anyo;
    }


    /**
     * Devuelve la revista en la que fue publicada la publicación
     * @return la revista
     */
    public String getRevista() {
        return revista;
    }


    /**
     * Devuelve <em>true</em> si <em>pId</em> es autor de la publicación y <em>false</em> en caso contrario
     * @param pId - ORCID del investigador
     * @return <em>true</em> si <em>pId</em> es autor de la publicación y <em>false</em> en caso contrario
     */
    public boolean esAutor(String pId) {
        return listaAutores.contains(pId);
    }

    /**
     * Devuelve el número de artículos que citan o referencian la publicación
     * @return el número de citas
     */
    public int getNumCitas() {
        return articulosQueLoCitan.size();
    }

}

