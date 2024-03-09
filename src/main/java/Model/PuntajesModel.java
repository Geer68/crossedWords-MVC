package Model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Stack;
import javax.swing.JOptionPane;

public class PuntajesModel {

    final private Stack<Jugador> jugadores = new Stack();
    private Jugador jugador;

    public PuntajesModel(String nombre, int puntos, String dificultad) {
        jugadores.add(new Jugador(nombre, puntos, dificultad));
    }

    public PuntajesModel()  {
        //this.cargarDatos();
    }

    public void saveJugador(String nombre, int puntos, String dificultad) {
        this.jugador = new Jugador(nombre, puntos, dificultad);
    }

    public boolean guardarDatos() {
        try {
            String rutaArchivo = System.getProperty("user.dir") + "/src/main/java/Model/datos.txt";
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo, true))) {
                writer.write(jugador.toString());
                writer.newLine();
            }
            return true;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "¡Atención! No se encontró el archivo. Jugá una para generarlo", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }

    public void cargarDatos() throws FileNotFoundException {
        try {
            String rutaArchivo = System.getProperty("user.dir") + "/src/main/java/Model/datos.txt";
            try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
                String linea;
                while ((linea = reader.readLine()) != null) {
                    Jugador jugador = Jugador.fromString(linea);
                    if (jugador != null) {
                        jugadores.add(jugador);
                    }
                }
            }
        } catch (IOException e) {
            throw new FileNotFoundException();
        }
    }

    public int calculatePoints(int segundos) {
        return switch (Model.getDificultad()) {
            case "Facil" ->
                182;
            case "Intermedio" ->
                182 + (int) (segundos * 1.5);
            case "Dificil" ->
                182 + (int) (segundos * 3.0);
            default ->
                0;
        };
    }

    

    public Stack<Jugador> getJugadoresOrdenados() {
        Collections.sort(jugadores, (jugador1, jugador2)
                -> Integer.compare(jugador2.getPuntos(), jugador1.getPuntos())
        );

        return jugadores;
    }
}
