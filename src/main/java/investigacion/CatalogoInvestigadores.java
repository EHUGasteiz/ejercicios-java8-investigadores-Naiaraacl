package investigacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Clase CatalogoInvestigadores
 */
public class CatalogoInvestigadores {
    private static final  CatalogoInvestigadores mCatalogoInvestigadores = new CatalogoInvestigadores();
    private final List<Investigador> listaInvestigadores;

    private CatalogoInvestigadores() {
        listaInvestigadores = new ArrayList<>();
    }

    /**
     * Devuelve la instancia única de CatalogoInvestigadores
     *
     * @return el catálogo de investigadores
     */
    public static CatalogoInvestigadores getCatalogoInvestigadores() {
        return mCatalogoInvestigadores;
    }

    /**
     * Añade un nuevo investigador al catálogo de investigadores
     *
     * @param pInvestigador - el nuevo investigador
     */
    public void addInvestigador(Investigador pInvestigador) {
        listaInvestigadores.add(pInvestigador);
    }

    /**
     * Devuelve la lista de investigadores registrados en el catálogo.
     *
     * @return la lista de investigagores
     */
    public List<Investigador> getListaInvestigadores() {
        return List.copyOf(listaInvestigadores);
    }


    /**
     * Devuelve la lista de investigadores ordenada por publicaciones
     *
     * @return la lista investigadores ordenada por publicaciones
     */
    public List<Investigador> getListaInvestigadoresOrdenadaPorPublicaciones() {
        // TODO: Ejercicio 7
        return null;
    }

    /**
     * Devuelve la media de citas por departamento
     *
     * @return la media citas por departamento
     */
    public Map<String, Double> getMediaCitasPorDepartamento() {
        // TODO: Ejercicio 8
        return null;

    }

    /**
     * Devuelve los nombres de los investigadores agrupados por departamento y h-index
     *
     * @return los investigadores agrupados por departamento y h-index
     */
    public Map<String, Map<Integer,List<String>>> getInvestigadoresPorDepYHIndex() {
        // TODO: Ejercicio 10
        return null;

    }


    /**
     * Devuelve la lista de investigadores ordenada por departamento y h-index
     *
     * @return lista de investigadores ordenada por departamento y h-index
     */
    public List<Investigador> getInvestigadoresOrdenadosPorDepYHIndex() {
        // TODO: Ejercicio 9
        return null;
    }

    /**
     * Elimina los investigadores del catálogo.
     */
    public void eliminaInvestigadores() {
        listaInvestigadores.clear();
    }
}

