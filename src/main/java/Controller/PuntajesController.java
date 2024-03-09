package Controller;

import Model.Jugador;
import Model.PuntajesModel;
import javax.swing.table.DefaultTableModel;
import View.PuntajesView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class PuntajesController implements ActionListener {

    final private PuntajesView view;
    final private PuntajesModel model;

    public PuntajesController(PuntajesView view, PuntajesModel model) {
        this.view = view;
        this.model = model;
        iniciarVista();
        view.getVolverButton().addActionListener(this);
    }

    public void iniciarVista() {
        try {
            model.cargarDatos();
            if (model.getJugadoresOrdenados().isEmpty()) {
                JOptionPane.showMessageDialog(null, "¡Atención! No se encontraron puntajes. Jugá generarlos", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
                view.setVisible(false);
                return;
            }
            view.setLocationRelativeTo(null);
            view.setVisible(true);
            
            fillTabla((DefaultTableModel) view.getTablaPuntajes().getModel());
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "¡Atención! No se encontró el archivo. Jugá para generarlo", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void fillTabla(DefaultTableModel tabla) {
        if (!model.getJugadoresOrdenados().isEmpty()) {
            for (Jugador jugador : model.getJugadoresOrdenados()) {
                Object[] rowData = {jugador.getNombre(), jugador.getPartida(), jugador.getPuntos()};
                tabla.addRow(rowData);
            }
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();
        if (clickedButton == view.getVolverButton()) {
            view.setVisible(false);
        }
    }
}
