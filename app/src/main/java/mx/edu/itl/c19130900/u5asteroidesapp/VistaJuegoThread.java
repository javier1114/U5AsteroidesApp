package mx.edu.itl.c19130900.u5asteroidesapp;

import android.util.Log;

public class VistaJuegoThread extends Thread {

    private VistaJuegoView vistaJuegoView;
    private int periodoSleep;

    public VistaJuegoThread (VistaJuegoView vistaJuegoView, int periodo) {
        super();
        this.vistaJuegoView = vistaJuegoView;
        periodoSleep = periodo;
    }

    @Override
    public void run() {
        boolean corriendo = vistaJuegoView.isCorriendo();
        while (corriendo) {
            corriendo = vistaJuegoView.isCorriendo();
            vistaJuegoView.actualizarFisica();
            try {
                Thread.sleep(periodoSleep);
            } catch (InterruptedException ex) {
                Log.e("Asteroides", ex.toString());
            }
        }
    }
}
