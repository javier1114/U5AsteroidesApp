package mx.edu.itl.c19130900.u5asteroidesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import mx.edu.itl.c19130900.u5asteroidesapp.R;

public class JuegoActivity extends AppCompatActivity {

    private VistaJuegoView vistaJuegoView;
    private MediaPlayer mplayAudioFondo;
    private MediaPlayer mplayAudioDisparo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.juego_layout);

        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);

        vistaJuegoView = findViewById(R.id.vistaJuegoView);

        mplayAudioFondo = MediaPlayer.create(this, R.raw.audio_fondo);
        mplayAudioFondo.setLooping(true);

        mplayAudioDisparo = MediaPlayer.create(this, R.raw.audio_disparo);
        vistaJuegoView.setAudioDisparo(mplayAudioDisparo);

        Toast.makeText(this, "Javier Arath de la Cerda Martinez (19130900)", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mplayAudioFondo != null)
            mplayAudioFondo.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mplayAudioFondo != null)
            mplayAudioFondo.pause();
    }

    @Override
    protected void onDestroy() {
        if(mplayAudioFondo != null)
            mplayAudioFondo.stop();
        if(mplayAudioDisparo != null)
            mplayAudioDisparo.stop();

        vistaJuegoView.setCorriendo(false);
        VistaJuegoThread hilo = vistaJuegoView.getVistaJuegoThread();
        try {
            hilo.join();
        } catch (InterruptedException ex) {
            Log.e("Asteroides", ex.toString());
        }
        super.onDestroy();
    }
}