package Controller;

import Model.Model;
import View.MenuView;

public class HidalgoCrossWords {

    public static void main(String[] args) {
        MenuView menuView = new MenuView();
        Model model = new Model();
        MenuController menuController = new MenuController(menuView, model);
    }
}
