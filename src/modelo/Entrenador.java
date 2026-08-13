package modelo;

/*
  Representa a un entrenador: su nombre, sus 4 Digimon, cuales ya uso,
  cuantas rondas ha ganado, y el bono pendiente que le dejo su
  digievolucion para la ronda siguiente (ataque y/o defensa extra).
 */
public class Entrenador {

    private String nombre;
    private Digimon[] digimones;
    private boolean[] utilizados;
    private int rondasGanadas;

    private int bonoAtaquePendiente;
    private int defensaExtraPendiente;

    public Entrenador(String nombre) {

        this.nombre = nombre;

        digimones = new Digimon[4];
        utilizados = new boolean[4];

        rondasGanadas = 0;
        bonoAtaquePendiente = 0;
        defensaExtraPendiente = 0;
    }

    public void agregarDigimon(Digimon digimon, int posicion) {

        if (posicion >= 0 && posicion < digimones.length) {
            digimones[posicion] = digimon;
        }
    }

    public Digimon getDigimon(int posicion) {

        if (posicion >= 0 && posicion < digimones.length) {
            return digimones[posicion];
        }

        return null;
    }

    public boolean fueUtilizado(int posicion) {

        if (posicion >= 0 && posicion < utilizados.length) {
            return utilizados[posicion];
        }

        return false;
    }

    public void marcarComoUtilizado(int posicion) {

        if (posicion >= 0 && posicion < utilizados.length) {
            utilizados[posicion] = true;
        }
    }

    public void ganarRonda() {
        rondasGanadas++;
    }

    /*
      Suma un valor al bono de ataque pendiente (puede ser negativo,
      por ejemplo cuando el rival te aplica dano directo).
     */
    public void agregarBonoAtaque(int valor) {
        bonoAtaquePendiente += valor;
    }

    /*
      Devuelve el bono de ataque acumulado y lo reinicia a 0,
      ya que solo aplica para el Digimon que juega esta ronda.
     */
    public int consumirBonoAtaque() {
        int valor = bonoAtaquePendiente;
        bonoAtaquePendiente = 0;
        return valor;
    }

    public void agregarDefensaExtra(int valor) {
        defensaExtraPendiente += valor;
    }

    public int consumirDefensaExtra() {
        int valor = defensaExtraPendiente;
        defensaExtraPendiente = 0;
        return valor;
    }

    public String getNombre() {
        return nombre;
    }

    public int getRondasGanadas() {
        return rondasGanadas;
    }

    public Digimon[] getDigimones() {
        return digimones;
    }
}