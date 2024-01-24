package com.example.aptmc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ScaleName extends AppCompatActivity {

    private Button backButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scale_name);

        String selectedRoot = getIntent().getStringExtra("ROOT_SELECTION");
        String selectedScaleType = getIntent().getStringExtra("SCALE_TYPE_SELECTION");
        TextView textViewScaleName = findViewById(R.id.textViewScaleName);
        textViewScaleName.setText(selectedRoot + " " + selectedScaleType);

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScaleName.this, ScaleCalculator.class);
                startActivity(intent);
            }
        });

        // Creamos una instancia de la clase Beethoven y obtenemos la escala
        Beethoven beethoven = new Beethoven();
        String[] scale = beethoven.createScale(selectedRoot, selectedScaleType);



    }
}
