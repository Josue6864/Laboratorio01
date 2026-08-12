package controlador;

import modelo.Batalla;
import modelo.Digimon;
import modelo.Entrenador;

public class ControladorBatalla {

    private Batalla batalla;

    public ControladorBatalla(Entrenador entrenador1, Entrenador entrenador2) {
        this.batalla = new Batalla(entrenador1, entrenador2);
    }

    public boolean puedeUsarDigimon(Entrenador entrenador, int posicion) {

        if (posicion < 0 || posicion >= 4) {
            return false;
        }

        if (entrenador.getDigimon(posicion) == null) {
            return false;
        }

        if (entrenador.fueUtilizado(posicion)) {
            return false;
        }

        return true;
    }

    public Digimon seleccionarDigimon(Entrenador entrenador, int posicion) {

        if (!puedeUsarDigimon(entrenador, posicion)) {
            return null;
        }

        entrenador.marcarComoUtilizado(posicion);

        return entrenador.getDigimon(posicion);
    }

  
    public int calcularAtaque(Digimon atacante, Digimon rival) {

        int ataqueTotal = atacante.getAtaque();

        ataqueTotal += atacante.calcularEfectoTipo(rival);

        return ataqueTotal;
    }

    public Batalla getBatalla() {
        return batalla;
    }
}
