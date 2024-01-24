package Model;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Cronometro {

    private int segundosRestantes;
    private List<CronometroListener> listeners;
    private Timer timer;

    public Cronometro(int segundos) {
        this.segundosRestantes = segundos;
        this.listeners = new ArrayList<>();
    }

    public void agregarListener(CronometroListener listener) {
        listeners.add(listener);
    }

    public void startTimer() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                segundosRestantes--;
                notificarListeners();
                if (segundosRestantes <= 0) {
                    stopTimer();
                }
            }
        });
        timer.start();
    }

    private void notificarListeners() {
        for (CronometroListener listener : listeners) {
            listener.actualizarSegundosRestantes(segundosRestantes);
        }
    }

    public void stopTimer() {
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }
    }
}
