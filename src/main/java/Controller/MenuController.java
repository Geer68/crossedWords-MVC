package Controller;

import Model.Model;
import View.MenuView;
import View.View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

public class MenuController implements ActionListener {

    final private MenuView vista;
    final private Model model;
    private String difSelect;

    public MenuController(MenuView vista, Model model) {
        this.vista = vista;
        this.model = model;
        vista.setVisible(true);
        vista.setTitle("Menú");

        vista.getCheckFacil().addActionListener(this);
        vista.getCheckIntermedio().addActionListener(this);
        vista.getCheckDificil().addActionListener(this);
        vista.getStartGame().addActionListener(this);
        vista.getPuntajes().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == vista.getCheckFacil() || source == vista.getCheckIntermedio() || source == vista.getCheckDificil()) {
            if(!checkDificultades(source)){
                difSelect = ((JCheckBox) source).getText();
            }
        } else if (source == vista.getStartGame()) {
            checkDificultades(source);
            iniciarJuego();
        } else if (source == vista.getPuntajes()) {
            System.out.println("Puntajes xdxd");
        }
    }

    public boolean checkDificultades(Object source) {
        if (!vista.getCheckFacil().isSelected() && !vista.getCheckIntermedio().isSelected() && !vista.getCheckDificil().isSelected()) {
            difSelect = null;
            return false;
        }
        if (vista.getCheckFacil().isSelected() && (vista.getCheckIntermedio().isSelected() || vista.getCheckDificil().isSelected())
                || vista.getCheckIntermedio().isSelected() && (vista.getCheckFacil().isSelected() || vista.getCheckDificil().isSelected())
                || vista.getCheckDificil().isSelected() && (vista.getCheckFacil().isSelected() || vista.getCheckIntermedio().isSelected())) {
            JOptionPane.showMessageDialog(null, "Solo se puede seleccionar una dificultad a la vez.", "Error", JOptionPane.ERROR_MESSAGE);
            ((JCheckBox) source).setSelected(false);
            return true;
        }
        return false;
    }

    private void iniciarJuego() {
        if (difSelect == null) {
            JOptionPane.showMessageDialog(null, "Debes seleccionar una dificultad antes de iniciar el juego.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            Model.setDificultad(difSelect);
            vista.setVisible(true);
            View v1 = new View();
            Model m1 = new Model();
            Controller controlador = new Controller(v1, m1);
        }
    }
}
