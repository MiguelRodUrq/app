package com.example.multi;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

public class StickmanView extends View {
    private Paint paint;
    private float jumpOffset = 0;
    private boolean isGoingUp = true;

    public StickmanView(Context context) {
        super(context);
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Fondo blanco
        canvas.drawColor(Color.WHITE);

        // Configuración del pincel
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(8);

        // Actualizar posición de salto
        if (isGoingUp) {
            jumpOffset -= 2; // Salto más lento
            if (jumpOffset <= -100) { // Salto más alto
                isGoingUp = false;
            }
        } else {
            jumpOffset += 2; // Salto más lento
            if (jumpOffset >= 0) {
                isGoingUp = true;
            }
        }

        // Coordenadas base del stickman
        float baseX = 300;
        float baseY = 500 + jumpOffset; // Stickman más abajo

        // Cabeza
        canvas.drawCircle(baseX, baseY - 100, 50, paint);

        // Cuerpo
        canvas.drawLine(baseX, baseY - 50, baseX, baseY + 100, paint);

        // Brazos largos levantados desde el torso
        canvas.drawLine(baseX, baseY, baseX - 70, baseY - 150, paint);
        canvas.drawLine(baseX, baseY, baseX + 70, baseY - 150, paint);

        // Piernas
        canvas.drawLine(baseX, baseY + 100, baseX - 50, baseY + 200, paint);
        canvas.drawLine(baseX, baseY + 100, baseX + 50, baseY + 200, paint);

        // Rectángulo (pancarta)
        paint.setColor(Color.BLUE);
        canvas.drawRect(baseX - 100, baseY - 200, baseX + 100, baseY - 150, paint);

        // Texto en la pancarta
        paint.setColor(Color.WHITE);
        paint.setTextSize(30);
        canvas.drawText("¡Ese Juanaaan!", baseX - 100, baseY - 170, paint);

        // Dibujar una elipse inclinada con el nombre "Miguel"
        paint.setColor(Color.RED); // Color de la elipse
        paint.setStyle(Paint.Style.STROKE); // Solo contorno
        paint.setStrokeWidth(5);

        // Definir los límites de la elipse
        float left = 500;
        float top = 300;
        float right = 800;
        float bottom = 400;

        // Rotar el canvas para inclinar la elipse
        canvas.save(); // Guardar el estado del canvas
        canvas.rotate(-30, (left + right) / 2, (top + bottom) / 2); // Rotar alrededor del centro de la elipse

        // Dibujar la elipse
        RectF oval = new RectF(left, top, right, bottom);
        canvas.drawOval(oval, paint);

        // Escribir el texto dentro de la elipse
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL); // Relleno para el texto
        paint.setTextSize(40);
        float textWidth = paint.measureText("Miguel");
        canvas.drawText("Miguel", (left + right) / 2 - textWidth / 2, (top + bottom) / 2 + 15, paint);

        // Restaurar el estado original del canvas
        canvas.restore();

        // Forzar redibujo para animación
        invalidate();
    }
}
