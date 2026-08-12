package modelo;

public class Entrenador {

    private String nombre;
    private Digimon[] digimones;
    private boolean[] utilizados;
    private int rondasGanadas;

    public Entrenador(String nombre) {

        this.nombre = nombre;

        digimones = new Digimon[4];
        utilizados = new boolean[4];

        rondasGanadas = 0;
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