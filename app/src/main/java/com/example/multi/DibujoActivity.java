// DibujoActivity.java
package com.example.multi;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class  DibujoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new com.example.multi.StickmanView(this));

    }
}
