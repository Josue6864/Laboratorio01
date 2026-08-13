package modelo;

/**
 * Administra el enfrentamiento completo de 4 rondas entre dos
 * entrenadores y determina el ganador general.
 */
public class Batalla {

    private Entrenador entrenador1;
    private Entrenador entrenador2;
    private int rondaActual;

    public Batalla(Entrenador entrenador1, Entrenador entrenador2) {
        this.entrenador1 = entrenador1;
        this.entrenador2 = entrenador2;
        this.rondaActual = 1;
    }

    public void avanzarRonda() {
        rondaActual++;
    }

    public boolean batallaTerminada() {
        return rondaActual > 4;
    }

    public Entrenador obtenerGanador() {

        if (entrenador1.getRondasGanadas() > entrenador2.getRondasGanadas()) {
            return entrenador1;
        }

        if (entrenador2.getRondasGanadas() > entrenador1.getRondasGanadas()) {
            return entrenador2;
        }

        return null;
    }

    public Entrenador getEntrenador1() {
        return entrenador1;
    }

    public Entrenador getEntrenador2() {
        return entrenador2;
    }

    public int getRondaActual() {
        return rondaActual;
    }
}