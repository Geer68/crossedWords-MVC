package Controller;

import Model.Cronometro;
import Model.JugadorModel;
import Model.Model;
import View.JugadorView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class JugadorController implements ActionListener {

    private JugadorView view;
    private JugadorModel model;
    private int segundos;

    public JugadorController(JugadorView view, JugadorModel model, int segundos) {
        this.view = view;
        this.model = model;
        this.segundos = segundos;
        this.view.setVisible(true);
        this.view.setTitle("Felicidades");
        this.view.getSaveGame().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == view.getSaveGame()) {
            System.out.println(view.getJugadorName().getText());
            model.saveJugador(view.getJugadorName().getText(), model.calculatePoints(segundos), Model.getDificultad());
            if (!model.guardarDatos()) {
                JOptionPane.showMessageDialog(null, "Error al guardar datos", "Error", JOptionPane.ERROR_MESSAGE);
            }
            JOptionPane.showMessageDialog(null, "Datos guardados con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            this.view.setVisible(false);
        }
    }
}
