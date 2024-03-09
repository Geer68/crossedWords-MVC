package Controller;

import Model.PuntajesModel;
import Model.Model;
import View.JugadorView;
import View.View;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

public class Controller implements ActionListener, CronometroListener {

    private Model model;
    private View view;
    private Cronometro cronometro;    
    private final Tablero tableroHanddler;
    private boolean winner = false;

    public Controller(View v1, Model m1) {
        this.model = m1;
        this.view = v1;
        this.tableroHanddler = new Tablero();
        view.getAbandonar().addActionListener(this);
        view.getGanar().addActionListener(this);
        loadTablero();
        loadTablas();
        v1.setVisible(true);
        cargarCronometros();
        v1.setTitle("Crossed Words");
        addFacilListeners();
    }

    private void loadTablas() {
        DefaultTableModel tHorizontal = (DefaultTableModel) view.getTablaHorizontal().getModel();
        DefaultTableModel tVertical = (DefaultTableModel) view.getTablaVertical().getModel();
        TablaPista p1 = new TablaPista();
        p1.pistasHorizontales(tHorizontal);
        p1.pistasVerticales(tVertical);
        p1.prettyTable(view.getTablaHorizontal());
        p1.prettyTable(view.getTablaVertical());
    }

    private void loadTablero() {
        loadTablero(view.getTablero());
        if("Facil".equals(Model.getDificultad())){
            view.getLabelTiempo().setVisible(false);
            view.getTiempo().setVisible(false);
        }
    }

    public void actualizarTiempo(int segundos) {
        view.getTiempo().setText(String.valueOf(segundos));
        if (!"Facil".equals(Model.getDificultad())) {
            if (segundos == 0) {
                JOptionPane.showMessageDialog(null, "¡Se acabó el tiempo!", "Fin del juego", JOptionPane.WARNING_MESSAGE);
                this.view.setVisible(false);
            }
            actualizarPuntos(checkPuntaje(view.getTablero()));
        }

    }

    private void addFacilListeners() {
        Component[] components = this.view.getTablero().getComponents();

        for (Component component : components) {
            if (component instanceof JTextField textField) {

                textField.getDocument().addDocumentListener(new DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        handdleTextChange();
                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        handdleTextChange();
                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {
                        // No relevante
                    }
                });
            }
        }
    }

    private void handdleTextChange() {
        actualizarPuntos(checkPuntaje(view.getTablero()));
    }

    public void actualizarPuntos(int puntos) {
        view.getPuntos().setText(String.valueOf(puntos));
    }

    public void winnerStart() {
        int segundos = Integer.parseInt(this.view.getTiempo().getText());
        JugadorView playerView = new JugadorView();
        PuntajesModel playerModel = new PuntajesModel();
        JugadorController controlador = new JugadorController(playerView, playerModel, segundos);
        this.view.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();
        if (clickedButton == view.getAbandonar()) {
            view.setVisible(false);
        }
        JButton ganarLabel = view.getGanar();
        if (e.getSource() == ganarLabel) {
            String input = JOptionPane.showInputDialog(view, "...");

            if (input != null && !input.isEmpty() && "ger".equals(input)) {
                hacerTrampa(view.getTablero());
            } else {
                System.out.println("Incorrecto");
            }
        }
    }

    @Override
    public void actualizarSegundosRestantes(int segundos) {
        actualizarTiempo(segundos); 
    }
    
    public void cargarCronometros() {
        
        if (!"Facil".equals(Model.getDificultad())) {
            if ("Dificil".equals(Model.getDificultad())) {
                cronometro = new Cronometro(60);
            } else if ("Intermedio".equals(Model.getDificultad())) {
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
        winnerStart();
        return puntos;
    }

    private void checkWinner(int puntos) {
        this.winner = puntos == 179;
    }
     public void hacerTrampa(JPanel tablero){
        tableroHanddler.llenarTrampa(tablero);
        checkPuntaje(tablero);
    }
     
     public void loadTablero(JPanel tablero) {
        tableroHanddler.configTablero(tablero);
    }

    public JTextField[][] getTableroValues() {
        return tableroHanddler.getTextFields();
    }
}
