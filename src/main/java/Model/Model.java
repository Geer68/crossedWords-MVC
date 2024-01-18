package Model;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class Model {
    private Tablero tableroHanddler;
    static private String dificultad;
    public Model(){
        this.tableroHanddler = new Tablero();
    }
    
    public void loadTablero(JPanel tablero){
        tableroHanddler.configTablero(tablero);
    }
    
    public JTextField[][] getTableroValues(){
        return tableroHanddler.getTextFields();
    }

    public static void setDificultad(String dificultad) {
        Model.dificultad = dificultad;
    }

    public static String getDificultad() {
        return dificultad;
    }
}
