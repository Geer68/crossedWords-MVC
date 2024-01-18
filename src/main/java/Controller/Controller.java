package Controller;

import Model.Cronometro;
import Model.CronometroListener;
import Model.Model;
import View.View;

public class Controller implements CronometroListener {

    private Model model;
    private View view;

    public Controller(View v1, Model m1) {
        this.model = m1;
        this.view = v1;
        loadTablero();
        v1.setVisible(true);
        cargarCronometros();
        v1.setTitle("Cross Words");
    }

    private void loadTablero() {
        model.loadTablero(view.getTablero());
    }
    
    private void cargarCronometros(){
        Cronometro cronometro = new Cronometro(60);
        cronometro.agregarListener(this);
        cronometro.startTimer();
    }
    
    @Override
    public void actualizarSegundosRestantes(int segundos){
        view.getTiempo().setText(String.valueOf(segundos));
    }
}
