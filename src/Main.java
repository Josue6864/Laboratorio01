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
                "Adult",
                EfectoDigievolucion.AUMENTAR_ATAQUE,
                15,
                30);

        Digievolucion mega = new Digievolucion(
                "Mega",
                EfectoDigievolucion.AUMENTAR_DEFENSA,
                20,
                30);

        Digievolucion ultimate = new Digievolucion(
                "Ultimate",
                EfectoDigievolucion.DANIO_DIRECTO,
                10,
                30);

        Digimon agumon = new Digimon(
                "Agumon",
                Tipo.FUEGO,
                50,
                30,
                adult);

        Digimon gabumon = new Digimon(
                "Gabumon",
                Tipo.AGUA,
                45,
                35,
                mega);

        Digimon palmon = new Digimon(
                "Palmon",
                Tipo.PLANTA,
                40,
                40,
                ultimate);

        Digimon tentomon = new Digimon(
                "Tentomon",
                Tipo.ELECTRICO,
                48,
                32,
                adult);

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

        /*
         * Aqui creo el objeto de tipo ControladorBatalla que sera necesario
         * para poder jugar la batalla entre los entrenadores. El controlador se encarga
         * de
         * manejar la logica de la batalla y de las validaciones
         */
        ControladorBatalla controlador = new ControladorBatalla(entrenador1, entrenador2);

        System.out.println("==============================");
        System.out.println("     BATALLA DE DIGIMON");
        System.out.println("==============================");

        while (!controlador.batallaTerminada()) {

            System.out.println();
            System.out.println(
                    "----- Ronda "
                            + controlador.getRondaActual()
                            + " -----");

            // Aqui se representa el turno del jugador 1

            System.out.println();

            mostrarDigimones(entrenador1);

            int opcion1 = seleccionarDigimonValido(
                    scanner,
                    controlador,
                    entrenador1);

            int opcionHabilidad1 = leerEnteroEnRango(
                    scanner,
                    entrenador1.getNombre()
                            + ", usar habilidad especial? "
                            + "(1=Si, 0=No): ",
                    0,
                    1);

            boolean usarHabilidad1 = opcionHabilidad1 == 1;

            // Aqui se representa el turno del jugador 2

            System.out.println();

            mostrarDigimones(entrenador2);

            int opcion2 = seleccionarDigimonValido(
                    scanner,
                    controlador,
                    entrenador2);

            int opcionHabilidad2 = leerEnteroEnRango(
                    scanner,
                    entrenador2.getNombre()
                            + ", usar habilidad especial? "
                            + "(1=Si, 0=No): ",
                    0,
                    1);

            boolean usarHabilidad2 = opcionHabilidad2 == 1;

            controlador.jugarRonda(
                    entrenador1,
                    opcion1 - 1,
                    usarHabilidad1,
                    entrenador2,
                    opcion2 - 1,
                    usarHabilidad2);

            if (!controlador.isUltimaRondaValida()) {

                System.out.println();
                System.out.println(
                        "Seleccion invalida. "
                                + "Esa posicion ya fue usada o no existe.");

            } else {

                System.out.println();
                System.out.println("Resultado de la ronda:");

                System.out.println(
                        entrenador1.getNombre()
                                + " - ataqueTotal: "
                                + controlador.getUltimoAtaqueTotal1()
                                + (controlador.isHabilidad1Activada()
                                        ? " (habilidad activada)"
                                        : ""));

                System.out.println(
                        entrenador2.getNombre()
                                + " - ataqueTotal: "
                                + controlador.getUltimoAtaqueTotal2()
                                + (controlador.isHabilidad2Activada()
                                        ? " (habilidad activada)"
                                        : ""));

                if (controlador.getUltimoAtaqueTotal1() > controlador.getUltimoAtaqueTotal2()) {

                    System.out.println(
                            "Gana la ronda: "
                                    + entrenador1.getNombre());

                } else if (controlador.getUltimoAtaqueTotal2() > controlador.getUltimoAtaqueTotal1()) {

                    System.out.println(
                            "Gana la ronda: "
                                    + entrenador2.getNombre());

                } else {

                    System.out.println(
                            "La ronda termino en empate.");
                }

                controlador.avanzarRonda();
            }
        }

        System.out.println();
        System.out.println("==============================");
        System.out.println("       RESULTADO FINAL");
        System.out.println("==============================");

        System.out.println(
                entrenador1.getNombre()
                        + " gano "
                        + entrenador1.getRondasGanadas()
                        + " rondas.");

        System.out.println(
                entrenador2.getNombre()
                        + " gano "
                        + entrenador2.getRondasGanadas()
                        + " rondas.");

        Entrenador ganador = controlador.obtenerGanadorFinal();

        if (ganador != null) {

            System.out.println(
                    "Ganador de la batalla: "
                            + ganador.getNombre());

        } else {

            System.out.println(
                    "La batalla termino en empate.");
        }

        scanner.close();
    }
    // Funcion para validar que el rango sea el adecuado.

    public static int leerEnteroEnRango(
            Scanner scanner,
            String mensaje,
            int minimo,
            int maximo) {

        int numero = 0;
        boolean valido = false;

        while (!valido) {

            System.out.print(mensaje);

            if (scanner.hasNextInt()) {

                numero = scanner.nextInt();

                if (numero >= minimo && numero <= maximo) {

                    valido = true;

                } else {

                    System.out.println(
                            "Ingrese un numero entre "
                                    + minimo
                                    + " y "
                                    + maximo
                                    + ".");
                }

            } else {

                System.out.println(
                        "Entrada invalida. Debe ingresar un numero.");

                scanner.next();
            }
        }

        return numero;
    }

    /*
     * Esta funcion es para mostrar los digimones de cada entrenador
     * 
     */
    public static void mostrarDigimones(
            Entrenador entrenador) {

        System.out.println(
                "Digimon de "
                        + entrenador.getNombre()
                        + ":");

        Digimon[] digimones = entrenador.getDigimones();

        for (int i = 0; i < digimones.length; i++) {

            System.out.print(
                    (i + 1)
                            + ". "
                            + digimones[i].getNombre());

            // Aqui se indica visualmente si ya utilizamos un digiamon
            if (entrenador.fueUtilizado(i)) {
                System.out.print(" [USADO]");
            }

            System.out.println();
        }
    }

    /*
      Este metodo nos sirve para seleccionar un digimon valido, es decir, que no
      haya sido usado y que este en el rango de 1 a 4
     */

    public static int seleccionarDigimonValido(
            Scanner scanner,
            ControladorBatalla controlador,
            Entrenador entrenador) {

        int opcion = 0;
        boolean valido = false;

        while (!valido) {

            // Primero valida que sea un numero entre 1 y 4
            opcion = leerEnteroEnRango(
                    scanner,
                    entrenador.getNombre()
                            + ", seleccione Digimon: ",
                    1,
                    4);

            if (controlador.puedeUsarDigimon(
                    entrenador,
                    opcion - 1)) {

                valido = true;

            } else {

                System.out.println(
                        "Ese Digimon ya fue utilizado. "
                                + "Seleccione otro.");
            }
        }

        return opcion;
    }
}