package com.example.aptmc;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ScaleName extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scale_name);

        String selectedRoot = getIntent().getStringExtra("ROOT_SELECTION");
        String selectedScaleType = getIntent().getStringExtra("SCALE_TYPE_SELECTION");

        // Creamos una instancia de la clase Beethoven y obtenemos la escala
        Beethoven beethoven = new Beethoven();
        String[] scale = beethoven.createScale(selectedRoot, selectedScaleType);

        TextView textViewScaleName = findViewById(R.id.textViewScaleName);
        textViewScaleName.setText(selectedRoot + " " + selectedScaleType);

    }
}
