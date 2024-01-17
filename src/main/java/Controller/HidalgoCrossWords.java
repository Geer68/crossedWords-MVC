package Controller;

import View.View;
import Model.Model;

public class HidalgoCrossWords {
    

    public static void main(String[] args) {
        View v1 = new View();
        Model m1 = new Model();
        Controller controller = new Controller(v1, m1);
    }
}
