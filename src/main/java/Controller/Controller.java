package Controller;

import Model.JugadorModel;
import Model.Model;
import Model.TablaPista;
import View.JugadorView;
import View.View;
import java.awt.Component;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

public class Controller {

    private Model model;
    private View view;

    public Controller(View v1, Model m1) {
        this.model = m1;
        this.view = v1;
        this.model.setController(this);
        loadTablero();
        loadTablas();
        v1.setVisible(true);
        m1.cargarCronometros();
        v1.setTitle("Cross Words");
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
        model.loadTablero(view.getTablero());
    }

    public void actualizarTiempo(int segundos) {
        view.getTiempo().setText(String.valueOf(segundos));
        if (!"Facil".equals(Model.getDificultad())) {
            actualizarPuntos(model.checkPuntaje(view.getTablero()));
        }

    }

    private void addFacilListeners() {
        Component[] components = this.view.getTablero().getComponents();

        for (Component component : components) {
            if (component instanceof JTextField textField) {

                // Agregar DocumentListener para escuchar cambios en el texto
                textField.getDocument().addDocumentListener(new DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        handleTextChange();
                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        handleTextChange();
                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {
                        // No relevante para este caso
                    }
                });
            }
        }
    }

    private void handleTextChange() {
        actualizarPuntos(model.checkPuntaje(view.getTablero()));
    }

    public void actualizarPuntos(int puntos) {
        view.getPuntos().setText(String.valueOf(puntos));
    }

    public void winnerStart() {
        int segundos = Integer.parseInt(this.view.getTiempo().getText());
        JugadorView playerView = new JugadorView();
        JugadorModel playerModel = new JugadorModel();
        JugadorController controlador = new JugadorController(playerView, playerModel, segundos);
    }

}
