// MainActivity.java
package com.example.multi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Asociar botones con el diseño XML
        Button btnDibujo = findViewById(R.id.btnDibujo);
        Button btnSonido = findViewById(R.id.btnSonido);
        Button btnVideo = findViewById(R.id.btnVideo);

        // Configurar evento del botón para abrir la actividad Dibujo
        btnDibujo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DibujoActivity.class);
                startActivity(intent);
            }
        });

        // Configurar evento del botón para abrir la actividad de Sonidos
        btnSonido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AudioPlayerActivity.class);
                startActivity(intent);
            }
        });

        // Configurar evento del botón para abrir la actividad de Video
        btnVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aquí puedes agregar la lógica para abrir tu actividad de video
            }
        });
        // En MainActivity.java
        btnVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, VideoActivity.class);
                startActivity(intent);
            }
        });

    }
}
