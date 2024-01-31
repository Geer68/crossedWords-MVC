package Model;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Arrays;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Tablero {

    private JTextField[][] textFields;

    private final Object[][] pistasNum = {
        {1, 0}, {2, 20}, {3, 2}, {7, 120}, {5, 44},
        {6, 25}, {4, 80}, {8, 85}, {9, 160}, {10, 106},
        {11, 87}, {12, 162}, {13, 242}, {14, 222}, {15, 224},
        {16, 30}, {17, 49}, {18, 15}, {19, 16}, {20, 55},
        {21, 95}, {22, 38}, {23, 93}, {24, 191}, {25, 137},
        {26, 228}, {27, 267}, {28, 210}, {29, 236}, {30, 279},
        {31, 302}, {32, 341}, {33, 232}, {34, 311}, {35, 298},
        {36, 296}, {37, 351}, {38, 383}, {39, 389}
    };

    String[] tableroUnidimensional = {
        "J", "#", "M", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "U", "R", "L", "#", "#",
        "S", "L", "O", "T", "#", "B", "U", "S", "#", "#", "S", "#", "#", "#", "#", "#", "E", "#", "D", "#",
        "#", "#", "U", "#", "P", "Y", "#", "#", "#", "M", "O", "U", "S", "E", "P", "A", "D", "#", "A", "#",
        "#", "#", "S", "#", "#", "T", "#", "#", "#", "#", "F", "#", "#", "#", "#", "C", "#", "#", "T", "#",
        "F", "U", "E", "N", "T", "E", "#", "C", "#", "#", "T", "#", "#", "L", "#", "C", "D", "R", "O", "M",
        "D", "#", "#", "#", "#", "#", "H", "A", "R", "D", "W", "A", "R", "E", "#", "E", "#", "#", "S", "#",
        "P", "#", "#", "#", "#", "#", "#", "R", "#", "#", "A", "#", "#", "C", "#", "S", "#", "M", "#", "#",
        "#", "#", "#", "#", "#", "#", "#", "P", "#", "#", "R", "#", "#", "T", "#", "S", "#", "E", "#", "#",
        "R", "#", "B", "O", "T", "O", "N", "E", "S", "#", "E", "#", "#", "O", "#", "#", "#", "M", "#", "#",
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

                final int finalContador = contador;

                Object[] pistaEncontrada = Arrays.stream(pistasNum)
                        .filter(pista -> (int) pista[1] == finalContador)
                        .findFirst()
                        .orElse(null);

                if (pistaEncontrada != null) {
                    setPlaceholder(textField, String.valueOf(pistaEncontrada[0]));
                }

                textField.setHorizontalAlignment(JTextField.CENTER);
                textField.setFont(new Font("Segoe UI", Font.BOLD, 12));

                if ("#".equals(tableroUnidimensional[contador])) {
                    textField.setForeground(Color.BLACK);
                    textField.setBackground(Color.BLACK);
                    textField.setEditable(false);
                }
                contador++;
            }
        }
    }
    
    public void llenarTrampa(JPanel tablero) {
        Component[] components = tablero.getComponents();
        int contador = 0;
        for (Component component : components) {
            if (component instanceof JTextField textField) {
                textField.setText(tableroUnidimensional[contador]);
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
                String letraIngresada = textField.getText().toUpperCase();
                String letraEsperada = tableroUnidimensional[i];

                if (!letraEsperada.isEmpty() && letraIngresada.equals(letraEsperada)) {
                    puntos++;
                }
            }
        }
        return puntos;
    }

    private static void setPlaceholder(JTextField textField, String placeholder) {
        textField.setForeground(Color.GRAY);
        textField.setText(placeholder);

        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setForeground(Color.GRAY);
                    textField.setText(placeholder);
                }
            }
        });
    }

    public JTextField[][] getTextFields() {
        return textFields;
    }
}
