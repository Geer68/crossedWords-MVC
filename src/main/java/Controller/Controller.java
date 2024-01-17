package Controller;

import Model.Model;
import View.View;

public class Controller {

    private Model model;
    private View view;

    public Controller(View v1, Model m1) {
        this.model = m1;
        this.view = v1;
        loadTablero();
        //v1.fillTablero(m1.getTableroValues());
        v1.setVisible(true);
        v1.setTitle("Cross Words");
    }

    private void loadTablero() {
        model.loadTablero(view.getTablero());
    }
}
