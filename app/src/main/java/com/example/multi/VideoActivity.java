package com.example.multi;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;

public class VideoActivity extends AppCompatActivity {

    private VideoView videoView;
    private MediaController mediaController;
    private Button btnFullscreen;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        // Referencias a los componentes de la interfaz
        videoView = findViewById(R.id.videoView);
        btnFullscreen = findViewById(R.id.btnFullscreen);
        btnBack = findViewById(R.id.btnBack);

        // Establecer el URI del video desde raw
        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.mi_video);
        videoView.setVideoURI(videoUri);

        // Configurar el MediaController para proporcionar los controles de reproducción
        mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);

        // Asignar el MediaController al VideoView
        videoView.setMediaController(mediaController);

        // Iniciar el video cuando está listo
        videoView.setOnPreparedListener(mp -> videoView.start());

        // Pantalla completa (fullscreen)
        btnFullscreen.setOnClickListener(v -> {
            // Cambiar el tamaño del VideoView para que ocupe toda la pantalla
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) videoView.getLayoutParams();

            // Si está en orientación vertical, hacer que ocupe toda la pantalla en horizontal
            if (getResources().getConfiguration().orientation == android.content.res.Configuration.ORIENTATION_PORTRAIT) {
                params.width = RelativeLayout.LayoutParams.MATCH_PARENT;
                params.height = RelativeLayout.LayoutParams.MATCH_PARENT;
            } else {
                // Si ya está en modo horizontal, puedes restaurar al tamaño anterior o lo que prefieras
                params.width = RelativeLayout.LayoutParams.MATCH_PARENT;
                params.height = 300; // tamaño original
            }

            // Aplicar los nuevos parámetros
            videoView.setLayoutParams(params);
            videoView.start();
        });

        // Acción para el botón "Volver"
        btnBack.setOnClickListener(v -> {
            // Finalizar la actividad y volver a la actividad anterior
            finish();
        });
    }
}
