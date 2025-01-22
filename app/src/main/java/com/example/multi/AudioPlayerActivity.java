package com.example.multi;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AudioPlayerActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer1;
    private MediaPlayer mediaPlayer2;
    private SeekBar volumeSeekBar;
    private SeekBar progressSeekBar;
    private AudioManager audioManager;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_player);

        // Inicializar botones de imagen
        Button soundButton1 = findViewById(R.id.soundButton1);
        Button soundButton2 = findViewById(R.id.soundButton2);
        Button backButton = findViewById(R.id.backButton);  // Botón para volver a la MainActivity

        // Inicializar SeekBars
        volumeSeekBar = findViewById(R.id.volumeSeekBar);
        progressSeekBar = findViewById(R.id.progressSeekBar);

        // Inicializar MediaPlayers
        mediaPlayer1 = MediaPlayer.create(this, R.raw.ic_sound1); // Reemplaza con tus archivos de sonido
        mediaPlayer2 = MediaPlayer.create(this, R.raw.ic_sound2);

        // Configuración de AudioManager para controlar el volumen
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        volumeSeekBar.setMax(maxVolume);
        volumeSeekBar.setProgress(currentVolume);

        // Control de volumen con la SeekBar
        volumeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // Botón para reproducir el primer sonido
        soundButton1.setOnClickListener(v -> playSound(mediaPlayer1));

        // Botón para reproducir el segundo sonido
        soundButton2.setOnClickListener(v -> playSound(mediaPlayer2));

        // Configuración de la SeekBar de progreso
        progressSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mediaPlayer1.seekTo(progress); // Cambiar al punto deseado
                    mediaPlayer2.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // Actualizar la SeekBar de progreso en tiempo real
        updateProgressBar();

        // Botón "Volver" que regresa a la MainActivity
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(AudioPlayerActivity.this, MainActivity.class);
            startActivity(intent);
            finish();  // Finaliza la actividad actual para evitar que el usuario regrese a ella al presionar "Atrás"
        });
    }

    private void playSound(MediaPlayer mediaPlayer) {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            Toast.makeText(this, "Pausa", Toast.LENGTH_SHORT).show();
        } else {
            mediaPlayer.start();
            Toast.makeText(this, "Reproduciendo", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateProgressBar() {
        handler.postDelayed(() -> {
            if (mediaPlayer1.isPlaying() || mediaPlayer2.isPlaying()) {
                MediaPlayer activePlayer = mediaPlayer1.isPlaying() ? mediaPlayer1 : mediaPlayer2;
                progressSeekBar.setMax(activePlayer.getDuration());
                progressSeekBar.setProgress(activePlayer.getCurrentPosition());
            }
            updateProgressBar();
        }, 1000); // Actualizar cada segundo
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer1 != null) {
            mediaPlayer1.release();
        }
        if (mediaPlayer2 != null) {
            mediaPlayer2.release();
        }
        handler.removeCallbacksAndMessages(null);
    }
}
