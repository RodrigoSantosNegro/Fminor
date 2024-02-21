package com.example.aptmc;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class ScaleName extends AppCompatActivity {

    private ImageButton favScaleButton;
    private ImageButton backButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scale_name);

        favScaleButton = findViewById(R.id.favButton);
        favScaleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ScaleName.this, "Si existiera base da datos se guardaría la escala en tus favoritos de perfil", Toast.LENGTH_LONG).show();
            }
        });

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScaleName.this, ScaleCalculator.class);
                startActivity(intent);
            }
        });

        String selectedRoot = getIntent().getStringExtra("ROOT_SELECTION");
        String selectedScaleType = getIntent().getStringExtra("SCALE_TYPE_SELECTION");
        //Copy of selectedScaleType
        String copySelectedScaleType = selectedScaleType;
        //Adaption of the String to Beethoven and screen title
        selectedScaleType = transformScaleTypeNameForBeethoven(selectedScaleType);
        copySelectedScaleType = transformScaleTypeNameForTitle(copySelectedScaleType);


        TextView textViewScaleName = findViewById(R.id.textViewScaleName);
        textViewScaleName.setText(selectedRoot + " " + copySelectedScaleType);


        // Creamos una instancia de la clase Beethoven y obtenemos la escala
        Beethoven beethoven = new Beethoven();
        String[] scaleName = beethoven.createScale(selectedRoot, selectedScaleType);
        //Imprimimos las notas de la escala calculada
        TextView textViewScaleNotes = findViewById(R.id.notes);
        textViewScaleNotes.setText(Arrays.toString(scaleName).replaceAll("\\[|\\]", "").trim());


        //Calculamos y escribimos las tríades de la escala
        String[] chords = beethoven.getScaleChords(selectedRoot, selectedScaleType);
        
        TextView textViewChordPosition1 = findViewById(R.id.chord1);
        TextView textViewChordPosition2 = findViewById(R.id.chord2);
        TextView textViewChordPosition3 = findViewById(R.id.chord3);
        TextView textViewChordPosition4 = findViewById(R.id.chord4);
        TextView textViewChordPosition5 = findViewById(R.id.chord5);
        TextView textViewChordPosition6 = findViewById(R.id.chord6);
        TextView textViewChordPosition7 = findViewById(R.id.chord7);

        showChordInfo(textViewChordPosition1, chords[0], beethoven, 1);
        showChordInfo(textViewChordPosition2, chords[1], beethoven, 2);
        showChordInfo(textViewChordPosition3, chords[2], beethoven, 3);
        showChordInfo(textViewChordPosition4, chords[3], beethoven, 4);
        showChordInfo(textViewChordPosition5, chords[4], beethoven, 5);
        showChordInfo(textViewChordPosition6, chords[5], beethoven, 6);
        showChordInfo(textViewChordPosition7, chords[6], beethoven, 7);

    }

    //Función para separar las notas del acorde, obtener el nombre del acorde y obtener el número romano en minúscula o mayúscula
    private void showChordInfo(TextView textView, String chord, Beethoven beethoven, int position) {
        String[] chordNotes = chord.split(", ");

        String chordName = beethoven.getChordName(chordNotes[0], chordNotes[1], chordNotes[2]);

        String romanNumeral = getRomanNumeral(position);
        romanNumeral = (chordName.contains("minor") || chordName.contains("diminished")) ? romanNumeral.toLowerCase() : romanNumeral;

        String styledText = String.format("<i>%s</i>- <b>%s</b>: %s", romanNumeral, chordName, String.join(", ", chordNotes));
        textView.setText(Html.fromHtml(styledText));

    }
    private String getRomanNumeral(int number) {
        switch (number) {
            case 1: return "I";
            case 2: return "II";
            case 3: return "III";
            case 4: return "IV";
            case 5: return "V";
            case 6: return "VI";
            case 7: return "VII";
            default: return"";
        }
    }

    private String transformScaleTypeNameForTitle(String originalName) {
        switch (originalName) {
            case "Ionian/Major":
                return "Major";
            case "Aeolian/Minor":
                return "Minor";
            default:
                return originalName;
        }
    }


    //Función que devuelve el string scaleType adaptado a las necesitades de Beethoven
    private static String transformScaleTypeNameForBeethoven(String originalName) {
        switch (originalName) {
            case "Ionian/Major":
                return "ionian";
            case "Aeolian/Minor":
                return "aeolian";
            case "Harmonic Minor":
                return "harmonicminor";
            case "Melodic Minor":
                return "melodicminor";
            default:
                return originalName;
        }
    }

}
