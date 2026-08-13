package modelo;
 
/*
  Representa los datos fijos de un Digimon: nombre, tipo, ataque, defensa
  base y su digievolucion. No guardan los estados de batalla — eso se administra en la clase Entrenador, para poder
  reutilizar el mismo Digimon entre distintos entrenadores sin que
  un bono de uno se filtre al otro.
 */
public class Digimon {
 
    private String nombre;
    private Tipo tipo;
    private int ataque;
    private int defensa;
    private Digievolucion digievolucion;
 
    public Digimon(String nombre, Tipo tipo, int ataque,
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
 
    public Tipo getTipo() {
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
 
    /**
      Calcula el bono/penalizacion de ataque segun el tipo de este
      Digimon contra el tipo del rival.
      Fuego > Planta, Planta > Agua, Agua > Fuego, Electrico > Agua : +20
      La relacion inversa de esas mismas parejas: -10
      Cualquier otra combinacion: 0 (neutral)
     */
    public int calcularEfectoTipo(Digimon rival) {
 
        if (tipo == Tipo.FUEGO && rival.getTipo() == Tipo.PLANTA) {
            return 20;
        }
        if (tipo == Tipo.PLANTA && rival.getTipo() == Tipo.AGUA) {
            return 20;
        }
        if (tipo == Tipo.AGUA && rival.getTipo() == Tipo.FUEGO) {
            return 20;
        }
        if (tipo == Tipo.ELECTRICO && rival.getTipo() == Tipo.AGUA) {
            return 20;
        }
 
        if (tipo == Tipo.FUEGO && rival.getTipo() == Tipo.AGUA) {
            return -10;
        }
        if (tipo == Tipo.PLANTA && rival.getTipo() == Tipo.FUEGO) {
            return -10;
        }
        if (tipo == Tipo.AGUA && rival.getTipo() == Tipo.PLANTA) {
            return -10;
        }
        if (tipo == Tipo.AGUA && rival.getTipo() == Tipo.ELECTRICO) {
            return -10;
        }
 
        return 0;
    }
}