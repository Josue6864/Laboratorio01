import java.util.Scanner;

import controlador.ControladorBatalla;
import modelo.Digievolucion;
import modelo.Digimon;
import modelo.EfectoDigievolucion;
import modelo.Entrenador;
import modelo.Tipo;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Digievolucion adult = new Digievolucion(
                "Adult", EfectoDigievolucion.AUMENTAR_ATAQUE, 15, 30);

        Digievolucion mega = new Digievolucion(
                "Mega", EfectoDigievolucion.AUMENTAR_DEFENSA, 20, 30);

        Digievolucion ultimate = new Digievolucion(
                "Ultimate", EfectoDigievolucion.DANIO_DIRECTO, 10, 30);

        Digimon agumon = new Digimon("Agumon", Tipo.FUEGO, 50, 30, adult);
        Digimon gabumon = new Digimon("Gabumon", Tipo.AGUA, 45, 35, mega);
        Digimon palmon = new Digimon("Palmon", Tipo.PLANTA, 40, 40, ultimate);
        Digimon tentomon = new Digimon("Tentomon", Tipo.ELECTRICO, 48, 32, adult);

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

        ControladorBatalla controlador = new ControladorBatalla(entrenador1, entrenador2);

        System.out.println("==============================");
        System.out.println("     BATALLA DE DIGIMON");
        System.out.println("==============================");

        while (!controlador.batallaTerminada()) {

            System.out.println();
            System.out.println("----- Ronda " + controlador.getRondaActual() + " -----");

            System.out.println();
            System.out.println("Digimon disponibles:");
            System.out.println("1. Agumon");
            System.out.println("2. Gabumon");
            System.out.println("3. Palmon");
            System.out.println("4. Tentomon");

            System.out.println();
            System.out.print(entrenador1.getNombre() + ", seleccione Digimon: ");
            int opcion1 = scanner.nextInt();

            System.out.print(entrenador1.getNombre() + ", usar habilidad especial? (1=Si, 0=No): ");
            boolean usarHabilidad1 = scanner.nextInt() == 1;

            System.out.print(entrenador2.getNombre() + ", seleccione Digimon: ");
            int opcion2 = scanner.nextInt();

            System.out.print(entrenador2.getNombre() + ", usar habilidad especial? (1=Si, 0=No): ");
            boolean usarHabilidad2 = scanner.nextInt() == 1;

            controlador.jugarRonda(
                    entrenador1, opcion1 - 1, usarHabilidad1,
                    entrenador2, opcion2 - 1, usarHabilidad2);

            if (!controlador.isUltimaRondaValida()) {
                System.out.println();
                System.out.println("Seleccion invalida. Esa posicion ya fue usada o no existe.");
            } else {

                System.out.println();
                System.out.println(entrenador1.getNombre() + " - ataqueTotal: "
                        + controlador.getUltimoAtaqueTotal1()
                        + (controlador.isHabilidad1Activada() ? " (habilidad activada)" : ""));

                System.out.println(entrenador2.getNombre() + " - ataqueTotal: "
                        + controlador.getUltimoAtaqueTotal2()
                        + (controlador.isHabilidad2Activada() ? " (habilidad activada)" : ""));

                if (controlador.getUltimoAtaqueTotal1() > controlador.getUltimoAtaqueTotal2()) {
                    System.out.println("Gana la ronda: " + entrenador1.getNombre());
                } else if (controlador.getUltimoAtaqueTotal2() > controlador.getUltimoAtaqueTotal1()) {
                    System.out.println("Gana la ronda: " + entrenador2.getNombre());
                } else {
                    System.out.println("La ronda termino en empate.");
                }

                controlador.avanzarRonda();
            }
        }

        System.out.println();
        System.out.println("==============================");
        System.out.println("     RESULTADO FINAL");
        System.out.println("==============================");
        System.out.println(entrenador1.getNombre() + " gano " + entrenador1.getRondasGanadas() + " rondas");
        System.out.println(entrenador2.getNombre() + " gano " + entrenador2.getRondasGanadas() + " rondas");

        Entrenador ganador = controlador.obtenerGanadorFinal();

        if (ganador != null) {
            System.out.println("Ganador de la batalla: " + ganador.getNombre());
        } else {
            System.out.println("La batalla termino en empate.");
        }

        scanner.close();
    }
}