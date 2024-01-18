package Model;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Cronometro {

    private int segundosRestantes;
    private List<CronometroListener> listeners;

    public Cronometro(int segundos) {
        this.segundosRestantes = segundos;
        this.listeners = new ArrayList<>();
    }

    public void agregarListener(CronometroListener listener) {
        listeners.add(listener);
    }

    public void startTimer() {
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                segundosRestantes--;
                notificarListeners();
            }
        });
        timer.start();
    }

    private void notificarListeners() {
        for (CronometroListener listener : listeners) {
            listener.actualizarSegundosRestantes(segundosRestantes);
        }
    }
}
