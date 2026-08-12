import java.util.Scanner;

import controlador.ControladorBatalla;
import modelo.Digievolucion;
import modelo.Digimon;
import modelo.Entrenador;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);


        Digievolucion adult = new Digievolucion(
                "Adult",
                Digievolucion.AUMENTAR_ATAQUE,
                15,
                30
        );

        Digievolucion mega = new Digievolucion(
                "Mega",
                Digievolucion.AUMENTAR_DEFENSA,
                20,
                30
        );

        Digievolucion ultimate = new Digievolucion(
                "Ultimate",
                Digievolucion.DANIO_DIRECTO,
                10,
                30
        );



        Digimon agumon = new Digimon(
                "Agumon",
                Digimon.FUEGO,
                50,
                30,
                adult
        );

        Digimon gabumon = new Digimon(
                "Gabumon",
                Digimon.AGUA,
                45,
                35,
                mega
        );

        Digimon palmon = new Digimon(
                "Palmon",
                Digimon.PLANTA,
                40,
                40,
                ultimate
        );

        Digimon tentomon = new Digimon(
                "Tentomon",
                Digimon.ELECTRICO,
                48,
                32,
                adult
        );


        Entrenador entrenador1 = new Entrenador("Entrenador 1");
        Entrenador entrenador2 = new Entrenador("Entrenador 2");

        entrenador1.agregarDigimon(agumon, 0);
        entrenador1.agregarDigimon(gabumon, 1);
        entrenador1.agregarDigimon(palmon, 2);
        entrenador1.agregarDigimon(tentomon, 3);

        entrenador2.agregarDigimon(agumon, 0);
        entrenador2.agregarDigimon(gabumon, 1);
        entrenador2.agregarDigimon(palmon, 2);
        entrenador2.agregarDigimon(tentomon, 3);



        ControladorBatalla controlador =
                new ControladorBatalla(entrenador1, entrenador2);

        System.out.println("==============================");
        System.out.println("     BATALLA DE DIGIMON");
        System.out.println("==============================");

        System.out.println();
        System.out.println("Digimon disponibles:");
        System.out.println("1. Agumon");
        System.out.println("2. Gabumon");
        System.out.println("3. Palmon");
        System.out.println("4. Tentomon");

        System.out.println();
        System.out.print("Entrenador 1, seleccione Digimon: ");

        int opcion1 = scanner.nextInt();

        System.out.print("Entrenador 2, seleccione Digimon: ");

        int opcion2 = scanner.nextInt();


        Digimon elegido1 = controlador.seleccionarDigimon(
                entrenador1,
                opcion1 - 1
        );

        Digimon elegido2 = controlador.seleccionarDigimon(
                entrenador2,
                opcion2 - 1
        );


        if (elegido1 != null && elegido2 != null) {

            System.out.println();
            System.out.println("Combate:");

            System.out.println(
                    entrenador1.getNombre()
                    + ": "
                    + elegido1.getNombre()
            );

            System.out.println(
                    entrenador2.getNombre()
                    + ": "
                    + elegido2.getNombre()
            );



            int ataque1 = controlador.calcularAtaque(
                    elegido1,
                    elegido2
            );

            int ataque2 = controlador.calcularAtaque(
                    elegido2,
                    elegido1
            );



            System.out.println();
            System.out.println(
                    elegido1.getNombre()
                    + " - Ataque total: "
                    + ataque1
            );

            System.out.println(
                    elegido2.getNombre()
                    + " - Ataque total: "
                    + ataque2
            );



            if (ataque1 > ataque2) {

                System.out.println();
                System.out.println(
                        "Ganador: " + elegido1.getNombre()
                );

            } else if (ataque2 > ataque1) {

                System.out.println();
                System.out.println(
                        "Ganador: " + elegido2.getNombre()
                );

            } else {

                System.out.println();
                System.out.println("La ronda termino en empate.");
            }

        } else {

            System.out.println(
                    "La seleccion de Digimon no es valida."
            );
        }


        scanner.close();
    }
}