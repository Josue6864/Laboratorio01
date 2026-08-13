package controlador;

import modelo.Batalla;
import modelo.Digievolucion;
import modelo.Digimon;
import modelo.EfectoDigievolucion;
import modelo.Entrenador;

/*
  Contiene la logica del juego: valida selecciones de Digimon y
  resuelve una ronda completa digamos si es un ataque base + efecto de tipo +
  defensa del rival + digievolucion si se decide usar y se activa.
 */
public class ControladorBatalla {

    private Batalla batalla;

    private int ultimoAtaqueTotal1;
    private int ultimoAtaqueTotal2;
    private boolean habilidad1Activada;
    private boolean habilidad2Activada;
    private boolean ultimaRondaValida;

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

    private Digimon seleccionarDigimon(Entrenador entrenador, int posicion) {

        if (!puedeUsarDigimon(entrenador, posicion)) {
            return null;
        }

        entrenador.marcarComoUtilizado(posicion);
        return entrenador.getDigimon(posicion);
    }

    /**
      Resuelve una ronda completa entre los Digimon elegidos por cada
      entrenador. Actualiza rondasGanadas del entrenador que gane y
      tambien deja el resultado disponible en los getters de ultimo.
     */
    public void jugarRonda(Entrenador entrenador1, int posicion1, boolean usarHabilidad1,
                            Entrenador entrenador2, int posicion2, boolean usarHabilidad2) {

        Digimon d1 = seleccionarDigimon(entrenador1, posicion1);
        Digimon d2 = seleccionarDigimon(entrenador2, posicion2);

        if (d1 == null || d2 == null) {
            ultimaRondaValida = false;
            return;
        }

        ultimaRondaValida = true;
        habilidad1Activada = false;
        habilidad2Activada = false;

        int defensa1 = d1.getDefensa() + entrenador1.consumirDefensaExtra();
        int defensa2 = d2.getDefensa() + entrenador2.consumirDefensaExtra();

        int ataqueTotal1 = d1.getAtaque() + d1.calcularEfectoTipo(d2)
                - defensa2 + entrenador1.consumirBonoAtaque();

        int ataqueTotal2 = d2.getAtaque() + d2.calcularEfectoTipo(d1)
                - defensa1 + entrenador2.consumirBonoAtaque();

        if (usarHabilidad1 && d1.getDigievolucion().seActiva()) {
            habilidad1Activada = true;
            Digievolucion evo1 = d1.getDigievolucion();

            if (evo1.getTipoEfecto() == EfectoDigievolucion.AUMENTAR_ATAQUE) {
                ataqueTotal1 += evo1.getCantidadEfecto();
                entrenador1.agregarBonoAtaque(evo1.getCantidadEfecto());
            }
            if (evo1.getTipoEfecto() == EfectoDigievolucion.AUMENTAR_DEFENSA) {
                ataqueTotal2 -= evo1.getCantidadEfecto();
                entrenador1.agregarDefensaExtra(evo1.getCantidadEfecto());
            }
            if (evo1.getTipoEfecto() == EfectoDigievolucion.DANIO_DIRECTO) {
                ataqueTotal2 -= evo1.getCantidadEfecto();
                entrenador2.agregarBonoAtaque(-evo1.getCantidadEfecto());
            }
        }

        if (usarHabilidad2 && d2.getDigievolucion().seActiva()) {
            habilidad2Activada = true;
            Digievolucion evo2 = d2.getDigievolucion();

            if (evo2.getTipoEfecto() == EfectoDigievolucion.AUMENTAR_ATAQUE) {
                ataqueTotal2 += evo2.getCantidadEfecto();
                entrenador2.agregarBonoAtaque(evo2.getCantidadEfecto());
            }
            if (evo2.getTipoEfecto() == EfectoDigievolucion.AUMENTAR_DEFENSA) {
                ataqueTotal1 -= evo2.getCantidadEfecto();
                entrenador2.agregarDefensaExtra(evo2.getCantidadEfecto());
            }
            if (evo2.getTipoEfecto() == EfectoDigievolucion.DANIO_DIRECTO) {
                ataqueTotal1 -= evo2.getCantidadEfecto();
                entrenador1.agregarBonoAtaque(-evo2.getCantidadEfecto());
            }
        }

        ultimoAtaqueTotal1 = ataqueTotal1;
        ultimoAtaqueTotal2 = ataqueTotal2;

        if (ataqueTotal1 > ataqueTotal2) {
            entrenador1.ganarRonda();
        } else if (ataqueTotal2 > ataqueTotal1) {
            entrenador2.ganarRonda();
        }
        // si llegan a ser iguales, es empate de ronda: nadie suma puntos
    }

    public void avanzarRonda() {
        batalla.avanzarRonda();
    }

    public boolean batallaTerminada() {
        return batalla.batallaTerminada();
    }

    public Entrenador obtenerGanadorFinal() {
        return batalla.obtenerGanador();
    }

    public int getRondaActual() {
        return batalla.getRondaActual();
    }

    public boolean isUltimaRondaValida() {
        return ultimaRondaValida;
    }

    public int getUltimoAtaqueTotal1() {
        return ultimoAtaqueTotal1;
    }

    public int getUltimoAtaqueTotal2() {
        return ultimoAtaqueTotal2;
    }

    public boolean isHabilidad1Activada() {
        return habilidad1Activada;
    }

    public boolean isHabilidad2Activada() {
        return habilidad2Activada;
    }

    public Batalla getBatalla() {
        return batalla;
    }
}
