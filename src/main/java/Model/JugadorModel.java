package Model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JugadorModel {
    private List<Jugador> jugadores;
    private Jugador jugador;

    public JugadorModel(String nombre, int puntos, String dificultad) {
        this.jugador = new Jugador(nombre, puntos, dificultad);
    } 

    public JugadorModel() {
//        this.jugadores = new ArrayList<>();
//        cargarDatos();
    }
    public void saveJugador(String nombre, int puntos, String dificultad){
        this.jugador = new Jugador(nombre, puntos, dificultad);
    }

    public boolean guardarDatos() {
        try {
            String rutaArchivo = System.getProperty("user.dir") + "/src/main/java/Model/datos.txt";
            System.out.println(rutaArchivo);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo, true))) {
                writer.write(jugador.toString());
                writer.newLine();
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void cargarDatos() {
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
            e.printStackTrace();
        }
    }
    
    public int calculatePoints(int segundos) {
        return switch (Model.getDificultad()) {
            case "Facil" -> 182;
            case "Intermedio" -> 182 + (int) (segundos * 1.5);
            case "Dificil" -> 182 + (int) (segundos * 3.0);
            default -> 0;
        };
    }
}


