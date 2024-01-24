package Model;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Tablero {

    private JTextField[][] textFields;
    String[] tableroUnidimesional = {
        "J", "#", "M", "#", "#", "#", "#", "D", "#", "#", "#", "#", "#","#", "#", "U", "R", "L", "#", "#",
        "S", "L", "O", "T", "#", "B", "U", "S", "#", "#", "S", "#", "#", "#", "#", "#", "E", "#", "D", "#",
        "#", "#", "U", "#", "P", "Y", "#", "#", "#", "M", "O", "U", "S", "E", "P", "A", "D", "#", "A", "#",
        "#", "#", "S", "#", "#", "T", "#", "#", "#", "#", "F", "#", "#", "#", "#", "C", "#", "#", "T", "#",
        "F", "U", "E", "N", "T", "E", "#", "C", "#", "#", "T", "#", "#", "L", "#", "C", "D", "R", "O", "M",
        "D", "#", "#", "#", "#", "#", "H", "A", "R", "D", "W", "A", "R", "E", "#", "E", "#", "#", "S", "#",
        "P", "#", "#", "#", "#", "#", "#", "R", "#", "#", "A", "#", "#", "C", "#", "S", "#", "M", "#", "#",
        "#", "#", "#", "#", "#", "#", "#", "P", "#", "#", "R", "#", "#", "T", "#", "S", "#", "E", "#", "#", "R", "#",
        "B", "O", "T", "O", "N", "E", "S", "#", "E", "#", "#", "O", "#", "#", "#", "M", "#", "#",
        "E", "#", "A", "#", "#", "#", "#", "T", "#", "#", "#", "S", "E", "R", "V", "I", "D", "O", "R", "#",
        "L", "#", "R", "#", "#", "#", "#", "A", "#", "#", "I", "#", "#", "C", "#", "#", "#", "R", "#", "#",
        "A", "#", "R", "A", "M", "#", "#", "#", "T", "E", "C", "L", "A", "D", "O", "#", "B", "I", "T", "#",
        "C", "#", "A", "#", "O", "#", "#", "#", "#", "#", "O", "#", "R", "#", "#", "#", "#", "A", "#", "#",
        "I", "#", "#", "#", "N", "#", "#", "W", "#", "#", "N", "#", "C", "#", "#", "#", "#", "#", "#", "B",
        "O", "#", "#", "#", "I", "#", "#", "I", "#", "#", "O", "#", "H", "#", "#", "#", "W", "O", "R", "D",
        "N", "#", "I", "N", "T", "E", "R", "N", "E", "T", "#", "D", "I", "S", "C", "O", "#", "#", "O", "#",
        "#", "#", "#", "#", "O", "#", "#", "D", "#", "#", "#", "#", "V", "#", "#", "#", "#", "#", "O", "#",
        "#", "I", "M", "P", "R", "E", "S", "O", "R", "A", "#", "M", "O", "D", "E", "M", "#", "#", "T", "#",
        "#", "#", "#", "#", "#", "#", "#", "W", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#",
        "#", "#", "#", "M", "A", "C", "O", "S", "#", "M", "I", "C", "R", "O", "S", "O", "F", "T", "#", "#",};

    public void configTablero(JPanel tablero) {
        Component[] components = tablero.getComponents();
        int contador = 0;
        for (Component component : components) {
            if (component instanceof JTextField textField) {
                //textField.setText(tableroUnidimesional[contador]);
                textField.setHorizontalAlignment(JTextField.CENTER);
                textField.setFont(new Font("Seoge UI", Font.BOLD, 12));
                if("#".equals(tableroUnidimesional[contador])){
                    textField.setForeground(Color.black);
                    textField.setBackground(Color.BLACK);
                    textField.setEditable(false);
                } else {
                    textField.setForeground(Color.black);
                    textField.setBackground(Color.WHITE);
                }
//                
//                
//                if (textField.getText().equals("#")) {
//                    textField.setForeground(Color.black);
//                    textField.setBackground(Color.BLACK);
//                    textField.setEditable(false);
//                }
                contador++;
            }
        }
    }

    public int checkBoxesPoint(JPanel tablero) {
        Component[] components = tablero.getComponents();
        int puntos = 0;

        for (int i = 0; i < components.length; i++) {
            if (components[i] instanceof JTextField) {
                JTextField textField = (JTextField) components[i];
                String letraIngresada = textField.getText().toUpperCase(); // Convertir a mayúsculas para evitar diferencias de mayúsculas/minúsculas
                String letraEsperada = tableroUnidimesional[i];

                if (!letraEsperada.isEmpty() && letraIngresada.equals(letraEsperada)) {
                    puntos++;
                }
            }
        }

        return puntos;
    }

    public JTextField[][] getTextFields() {
        return textFields;
    }
}
