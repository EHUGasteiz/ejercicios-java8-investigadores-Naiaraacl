package investigacion;

import java.time.LocalDate;
import java.time.Period;
import java.util.stream.IntStream;

import static java.lang.String.format;


/**
 * Clase Investigador
 */
public class Investigador {
    private final String id;
    private final String nombre;
    private final LocalDate fechaNacimiento;
    private final String departamento;

    /**
     * Crea una nueva instancia de investigador
     *
     * @param pId              - Identificador del investigador
     * @param pNombre          - Nombre del investigador
     * @param pDepartamento    - Departamento al que pertenece el investigador
     * @param pFechaNacimiento - Fecha de nacimiento del investigador
     */
    public Investigador(String pId, String pNombre, String pDepartamento, LocalDate pFechaNacimiento) {
        super();
        id = pId;
        nombre = pNombre;
        departamento = pDepartamento;
        fechaNacimiento = pFechaNacimiento;
    }

    /**
     * Devuelve el identificador del investigador
     *
     * @return el identificador (ORCID) del investigador
     */
    public String getId() {
        return id;
    }

    /**
     * Devuelve el nombre del investigador
     *
     * @return el nombre del investigador
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Devuelve la fecha de nacimiento del investigador
     *
     * @return la fecha de nacimiento
     */
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Devuelve el departamento al que pertenece el investigador
     *
     * @return el departamento
     */
    public String getDepartamento() {
        return departamento;
    }

    /**
     * Devuelve el índice H del investigador
     * <p>
     * El índice H indica que es autor de al menos H publicaciones que han sido cada una referenciada H veces o más.
     *
     * @return el índice H
     */
    public int getHIndex() {//si es 56 entinces es que tiene al menos 56 publicaciones, citas o referencias
        // TODO: Ejercicio 5 (no aparece en el examen)
        return IntStream
                .iterate(getNumeroPublicaciones(), h->h-1)//es una especie de while
                .limit(getNumeroPublicaciones())
                .filter(h-> getNumPublicacionesConAlMenosCitas(h)>=h)//descartar valores de h tal que el num de publicaciones con al menos h referencias sea menos
                .findFirst() //nos quedamos con el primero
                .orElse(0); //en el casod e que no hayamos identificado ningun valor h, el valor devolvera 0
    }

    /**
     * Devuelve el número de publicaciones que han tenido como mínimo <em>pNum</em> citas
     *
     * @param pNum - el número mínimo de citas
     * @return el número de publicaciones que han tenido al menos <em>pNum</em> citas
     */
    public long getNumPublicacionesConAlMenosCitas(int pNum) {
        // TODO: Ejercicio 4
        return ListaPublicaciones.getListaPublicaciones()
                .getPublicacionesInvestigador(id)
                .stream()
                .filter(p->p.getNumCitas()>=pNum)
                .count();
    }

    /**
     * Devuelve el número de publicaciones de las que es autor el investigador
     *
     * @return el número de publicaciones
     */
    public int getNumeroPublicaciones() {
        // TODO: Ejercicio 2
        return ListaPublicaciones.getListaPublicaciones()
                .getPublicacionesInvestigador(id).size();
    }

    /**
     * Devuelve el número de citas totales que ha tenido el trabajo del investigador
     *
     * @return el número de citas totales
     */
    public int getNumCitasTotales() {
        // TODO: Ejercicio 3
        return ListaPublicaciones.getListaPublicaciones()
                .getPublicacionesInvestigador(id)
                .stream()
                .mapToInt(Publicacion::getNumCitas) //convierte en un flujo de valores enteros
                .sum();
    }

    @Override
    public String toString() {
        return format("ORCID: %s Nombre: %s Departamento: %s H-Index: %d Num. publicaciones %d Num. Citas: %d",
                id,
                nombre,
                departamento,
                getHIndex(),
                getNumeroPublicaciones(),
                getNumCitasTotales());
    }

    /**
     * Devuelve la edad del investigador
     *
     * @return la edad
     */
    public int getAge() {
        return Period.between(getFechaNacimiento(), LocalDate.now()).getYears();
    }
}

