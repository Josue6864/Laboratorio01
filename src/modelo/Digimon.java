package modelo;

public class Digimon {

   
    public static final String FUEGO = "Fuego";
    public static final String AGUA = "Agua";
    public static final String PLANTA = "Planta";
    public static final String ELECTRICO = "Electrico";

   
    private String nombre;
    private String tipo;
    private int ataque;
    private int defensa;
    private Digievolucion digievolucion;

   
    public Digimon(String nombre, String tipo, int ataque,
                   int defensa, Digievolucion digievolucion) {

        this.nombre = nombre;
        this.tipo = tipo;
        this.ataque = ataque;
        this.defensa = defensa;
        this.digievolucion = digievolucion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getDefensa() {
        return defensa;
    }

    public Digievolucion getDigievolucion() {
        return digievolucion;
    }

    public int calcularEfectoTipo(Digimon rival) {

        if (tipo.equals(FUEGO) && rival.getTipo().equals(PLANTA)) {
            return 20;
        }

        if (tipo.equals(PLANTA) && rival.getTipo().equals(AGUA)) {
            return 20;
        }

        if (tipo.equals(AGUA) && rival.getTipo().equals(FUEGO)) {
            return 20;
        }

        if (tipo.equals(ELECTRICO) && rival.getTipo().equals(AGUA)) {
            return 20;
        }

        if (tipo.equals(FUEGO) && rival.getTipo().equals(AGUA)) {
            return -10;
        }

        if (tipo.equals(PLANTA) && rival.getTipo().equals(FUEGO)) {
            return -10;
        }

        if (tipo.equals(AGUA) && rival.getTipo().equals(PLANTA)) {
            return -10;
        }

        if (tipo.equals(AGUA) && rival.getTipo().equals(ELECTRICO)) {
            return -10;
        }

        return 0;
    }
}