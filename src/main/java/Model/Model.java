package Model;

import Controller.Controller;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Model implements CronometroListener {

    private Tablero tableroHanddler;
    private Controller controller;
    static private String dificultad;
    private boolean winner = false;
    private Cronometro cronometro;

    public Model() {
        this.tableroHanddler = new Tablero();
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void loadTablero(JPanel tablero) {
        tableroHanddler.configTablero(tablero);
    }

    public JTextField[][] getTableroValues() {
        return tableroHanddler.getTextFields();
    }

    public static void setDificultad(String dificultad) {
        Model.dificultad = dificultad;
    }

    public static String getDificultad() {
        return dificultad;
    }

    public void cargarCronometros() {
        if (!"Facil".equals(dificultad)) {
            if ("Dificil".equals(dificultad)) {
                cronometro = new Cronometro(60);
            } else if ("Intermedio".equals(dificultad)) {
                cronometro = new Cronometro(120);
            } else {
                cronometro = new Cronometro(60);
            }
            cronometro.agregarListener(this);
            cronometro.startTimer();
        }
    }

    public int checkPuntaje(JPanel tablero) {
        int puntos = tableroHanddler.checkBoxesPoint(tablero);
        checkWinner(puntos);
        if (!winner) {
            return puntos;
        }
        if (!"Facil".equals(Model.getDificultad())) {
            cronometro.stopTimer();
        }
        controller.winnerStart();
        return puntos;
    }

    private void checkWinner(int puntos) {
        this.winner = puntos == 2;
    }

    @Override
    public void actualizarSegundosRestantes(int segundos) {
        if (controller != null) {
            controller.actualizarTiempo(segundos);
        }
    }

}
