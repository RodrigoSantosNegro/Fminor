package com.example.aptmc;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {
    private ImageButton profileButton;
    private Button menuScaleCalculatorButton;
    private Button menuNoteidentificationButton;
    private Button menuChordIdentificationButton;
    private Button menuIntervalIdentificationButton;
    private Button menuNoteSoundButton;
    private Button menuIntervalSoundButton;
    private Context context = this;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        profileButton = findViewById(R.id.menu_imgbutton_profile);
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ScaleCalculator.class);
                startActivity(intent);
            }
        });

        menuScaleCalculatorButton = findViewById(R.id.menu_button_scalecalculator);
        menuNoteidentificationButton = findViewById(R.id.menu_button_noteid);
        menuChordIdentificationButton = findViewById(R.id.menu_button_chordid);
        menuIntervalIdentificationButton = findViewById(R.id.menu_button_intervalid);
        menuNoteSoundButton = findViewById(R.id.menu_button_notesound);
        menuIntervalSoundButton = findViewById(R.id.menu_button_intervalsound);

        menuScaleCalculatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ScaleCalculator.class);
                startActivity(intent);
            }
        });
        menuNoteidentificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ScaleCalculator.class);
                startActivity(intent);
            }
        });
        menuChordIdentificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ScaleCalculator.class);
                startActivity(intent);
            }
        });
        menuIntervalIdentificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ScaleCalculator.class);
                startActivity(intent);
            }
        });
        menuNoteSoundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ScaleCalculator.class);
                startActivity(intent);
            }
        });
        menuIntervalSoundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ScaleCalculator.class);
                startActivity(intent);
            }
        });


    }
}
