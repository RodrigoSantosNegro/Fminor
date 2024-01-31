package com.example.aptmc;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class NoteIdentification extends AppCompatActivity {
    private ImageView sheetImageView;
    private int currentScore = 0;
    private TextView scoreTextView;
    private Map<String, Integer> noteImageMap; // Maps note names to drawable IDs

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identification_note);

        sheetImageView = findViewById(R.id.sheetDisplay);
        scoreTextView = findViewById(R.id.scoreCounter); // Make sure you have a TextView for the score

        initializeNoteImageMap();
        setUpNoteButtons();
        loadRandomSheet();
    }

    // Call this method to load a random sheet
    private void loadRandomSheet() {
        // Choose a random note from the map keys
        List<String> notes = new ArrayList<>(noteImageMap.keySet());
        String randomNote = notes.get(new Random().nextInt(notes.size()));

        // Update the ImageView with the random note's image
        sheetImageView.setImageResource(noteImageMap.get(randomNote));

        // Store the chosen note in the ImageView's tag for later reference
        sheetImageView.setTag(randomNote);
    }

    // This method will be called when any note button is clicked
    public void onNoteButtonClick(View view) {
        String clickedNote = view.getTag().toString();
        String displayedNote = sheetImageView.getTag().toString();

        if (clickedNote.equals(displayedNote)) {
            currentScore++;
        } else {
            currentScore = 0;
        }

        // Update the score display
        scoreTextView.setText("Score: " + (currentScore));

        // Load a new random sheet for the next round
        loadRandomSheet();
    }

    private void initializeNoteImageMap() {
        // Initialize the map with note names and corresponding image drawable IDs
        noteImageMap = new HashMap<>();
        noteImageMap.put("A", R.drawable.note_a_1);
        noteImageMap.put("A#", R.drawable.note_asharp_1);
        noteImageMap.put("Ab", R.drawable.note_ab_1);
        noteImageMap.put("B", R.drawable.note_b_1);
        noteImageMap.put("Bb", R.drawable.note_bb_1);
        noteImageMap.put("C", R.drawable.note_c_1);
        noteImageMap.put("C#", R.drawable.note_csharp_1);
        noteImageMap.put("Db", R.drawable.note_db_1);
        noteImageMap.put("D", R.drawable.note_d_1);
        noteImageMap.put("D#", R.drawable.note_dsharp_1);
        noteImageMap.put("Eb", R.drawable.note_eb_2);
        noteImageMap.put("E", R.drawable.note_e_1);
        noteImageMap.put("F", R.drawable.note_f_1);
        noteImageMap.put("F#", R.drawable.note_fsharp_1);
        noteImageMap.put("Gb", R.drawable.note_gb_1);
        noteImageMap.put("G", R.drawable.note_g_1);
        noteImageMap.put("G#", R.drawable.note_gsharp_1);
        noteImageMap.put("C", R.drawable.note_c_2);
        noteImageMap.put("C#", R.drawable.note_csharp_2);
        noteImageMap.put("Db", R.drawable.note_db_2);
        noteImageMap.put("D", R.drawable.note_d_2);
        noteImageMap.put("D#", R.drawable.note_dsharp_2);
        noteImageMap.put("E", R.drawable.note_e_2);
        noteImageMap.put("F", R.drawable.note_f_2);
        noteImageMap.put("F#", R.drawable.note_fsharp_2);
        noteImageMap.put("Gb", R.drawable.note_gb_2);
        noteImageMap.put("G", R.drawable.note_g_2);
        noteImageMap.put("G#", R.drawable.note_gsharp_2);
    }

    private void setUpNoteButtons() {
        int[] buttonIds = new int[]{
                R.id.btnC, R.id.btnCSharp, R.id.btnD, R.id.btnDSharp,
                R.id.btnE, R.id.btnF, R.id.btnFSharp, R.id.btnG,
                R.id.btnGSharp, R.id.btnA, R.id.btnASharp, R.id.btnB,
                R.id.btnBFlat, R.id.btnDFlat, R.id.btnEFlat, R.id.btnGFlat, R.id.btnAFlat
        };

        for (int buttonId : buttonIds) {
            Button noteButton = findViewById(buttonId);
            noteButton.setOnClickListener(this::onNoteButtonClick);
            // Setting the tag as the note associated with the button
            noteButton.setTag(noteButton.getText().toString());
        }
    }


}