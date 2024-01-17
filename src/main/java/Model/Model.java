package Model;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class Model {
    private Tablero tableroHanddler;
    public Model(){
        this.tableroHanddler = new Tablero();
    }
    
    public void loadTablero(JPanel tablero){
        tableroHanddler.configTablero(tablero);
    }
    
    public JTextField[][] getTableroValues(){
        return tableroHanddler.getTextFields();
    }
}
