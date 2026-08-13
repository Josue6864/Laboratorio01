package modelo;

/**
 * Representa la habilidad especial (digievolucion) de un Digimon:
 * su nombre, qué tipo de efecto produce, cuánto vale ese efecto,
 * y la probabilidad (0-100) de que se active al usarla.
 */
public class Digievolucion {

    private String nombre;
    private EfectoDigievolucion tipoEfecto;
    private int cantidadEfecto;
    private int probabilidadActivacion;

    public Digievolucion(String nombre, EfectoDigievolucion tipoEfecto,
                          int cantidadEfecto, int probabilidadActivacion) {

        this.nombre = nombre;
        this.tipoEfecto = tipoEfecto;
        this.cantidadEfecto = cantidadEfecto;
        this.probabilidadActivacion = probabilidadActivacion;
    }

    /**
     * Tira un numero aleatorio entre 0 y 100 y determina si la
     * habilidad se activa segun su probabilidad de activacion.
     */
    public boolean seActiva() {
        int numeroAleatorio = (int) (Math.random() * 101);
        return numeroAleatorio <= probabilidadActivacion;
    }

    public String getNombre() {
        return nombre;
    }

    public EfectoDigievolucion getTipoEfecto() {
        return tipoEfecto;
    }

    public int getCantidadEfecto() {
        return cantidadEfecto;
    }

    public int getProbabilidadActivacion() {
        return probabilidadActivacion;
    }
}