package Controller;

import View.View;
import Model.Model;
import View.MenuView;

public class HidalgoCrossWords {
    

    public static void main(String[] args) {
        
        //archivo de texto
        
        MenuView menuView = new MenuView();
        Model model = new Model();
        MenuController menuController = new MenuController(menuView, model);
//        View v1 = new View();
//        Model m1 = new Model();
//        Controller controller = new Controller(v1, m1);
    }
}
