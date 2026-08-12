package modelo;

public class Digievolucion {

    public static final int AUMENTAR_ATAQUE = 1;
    public static final int AUMENTAR_DEFENSA = 2;
    public static final int DANIO_DIRECTO = 3;

    private String nombre;
    private int tipoEfecto;
    private int cantidadEfecto;
    private int probabilidadActivacion;

    public Digievolucion(String nombre, int tipoEfecto,
                         int cantidadEfecto, int probabilidadActivacion) {

        this.nombre = nombre;
        this.tipoEfecto = tipoEfecto;
        this.cantidadEfecto = cantidadEfecto;
        this.probabilidadActivacion = probabilidadActivacion;
    }

    public boolean seActiva() {
        int numeroAleatorio = (int) (Math.random() * 101);

        return numeroAleatorio <= probabilidadActivacion;
    }

    public String getNombre() {
        return nombre;
    }

    public int getTipoEfecto() {
        return tipoEfecto;
    }

    public int getCantidadEfecto() {
        return cantidadEfecto;
    }

    public int getProbabilidadActivacion() {
        return probabilidadActivacion;
    }
}