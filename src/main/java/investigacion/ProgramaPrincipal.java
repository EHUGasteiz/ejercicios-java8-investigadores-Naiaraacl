package investigacion;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class ProgramaPrincipal {

    public static void main(String[] args) {

        CatalogoInvestigadores catInv = CatalogoInvestigadores.getCatalogoInvestigadores();
        catInv.eliminaInvestigadores();
        catInv.addInvestigador(new Investigador("ORCID1", "Researcher1", "Dept1", LocalDate.of(1967, 7, 18)));
        catInv.addInvestigador(new Investigador("ORCID2", "Researcher2", "Dept2", LocalDate.of(1957, 6, 8)));
        catInv.addInvestigador(new Investigador("ORCID3", "Researcher3", "Dept2", LocalDate.of(1957, 6, 8)));


        ListaPublicaciones lPublicaciones = ListaPublicaciones.getListaPublicaciones();
        // Eliminar las publicaciones que pudiera haber previamente

        lPublicaciones.addPublicacion(new Publicacion("Tit1", Set.of("ORCID1", "ORCID3"), 2020, "Revista1", List.of("Art1", "Art2", "Art3")));

        lPublicaciones.addPublicacion(new Publicacion("Tit2", Set.of("ORCID1"), 2021, "Revista2", List.of("Art11")));

        lPublicaciones.addPublicacion(new Publicacion("Tit3", Set.of("ORCID1", "ORCID3"), 2022, "Revista1", List.of("Art13", "Art22", "Art31", "Art43")));

        lPublicaciones.addPublicacion(new Publicacion("Tit4", Set.of("ORCID1"), 2019, "Revista3", List.of("Art3")));


        System.out.println("Investigadores");
        System.out.println("==============");
        catInv.getListaInvestigadores().forEach(System.out::println);

        System.out.println("\nInvestigadores ordenados por publicaciones");
        System.out.println("==========================================");
        catInv.getListaInvestigadoresOrdenadaPorPublicaciones().forEach(System.out::println);


        // Información por departamento
        Map<String, Map<Integer, List<String>>> investigadoresPorDepYHIndex = catInv.getInvestigadoresPorDepYHIndex();
        System.out.println("\nInvestigadores clasificados por departamento y H-Index");
        System.out.println("=======================================================");

        investigadoresPorDepYHIndex.entrySet().stream().sorted(Entry.comparingByKey()).forEach(dep -> {
            System.out.printf("Departamento: %s%n", dep.getKey());
            // Imprimir los investigadores del departamento organizados por h-index
            dep.getValue().entrySet().stream().sorted(Entry.comparingByKey()).forEach(h -> {
                System.out.printf("\tH-Index: %d%n", h.getKey());
                h.getValue().forEach(a -> System.out.printf("\t\t%s%n", a));
            });
        });


        // Media citas por departamento
        Map<String, Double> mediaCitasPorDepartamento = catInv.getMediaCitasPorDepartamento();
        System.out.println("\nMedia de citas por departamento");
        System.out.println("================================");
        mediaCitasPorDepartamento.entrySet().stream().sorted(Entry.comparingByKey()).forEach(e -> System.out.printf("Departamento: %s Media citas: %.2f%n", e.getKey(), e.getValue()));


        // Investigadores ordenados por departamento y h-index
        List<Investigador> lOrdenada = catInv.getInvestigadoresOrdenadosPorDepYHIndex();
        System.out.println("\nInvestigadores ordenados por departamento y H-Index");
        System.out.println("=======================================================");
        lOrdenada.forEach(System.out::println);

    }


}

