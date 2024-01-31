package Model;

public class Jugador {

    private String nombre;
    private int puntos;
    private String partida;

    public Jugador(String nombre, int puntos, String partida) {
        this.nombre = nombre;
        this.puntos = puntos;
        this.partida = partida;
    }

    public Jugador() {
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuntos() {
        return puntos;
    }

    public String getPartida() {
        return partida;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public void setPartida(String partida) {
        this.partida = partida;
    }

    public String toString() {
        return nombre + "," + puntos + "," + partida;
    }

    public static Jugador fromString(String jugadorString) {
        String[] partes = jugadorString.split(",");
        if (partes.length == 3) {
            String nombre = partes[0];
            int puntos = Integer.parseInt(partes[1]);
            String partida = partes[2];
            return new Jugador(nombre, puntos, partida);
        } else {
            return null;
        }
    }
}
