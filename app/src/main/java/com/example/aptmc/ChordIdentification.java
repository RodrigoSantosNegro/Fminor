package com.example.aptmc;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ChordIdentification extends AppCompatActivity {
    private ImageView chordImageView;
    private TextView scoreTextView;
    private Spinner rootNoteSpinner, chordTypeSpinner;
    private String selectedRootNote, selectedChordType;
    private int currentScore = 0;
    private Map<String, Integer> chordImageMap;
    private String[] rootNotes;
    private String[] chordTypes;
    private List<String> chordKeys;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identification_chord);

        chordImageView = findViewById(R.id.sheetDisplay);
        scoreTextView = findViewById(R.id.scoreCounter);
        rootNoteSpinner = findViewById(R.id.rootListBox);
        chordTypeSpinner = findViewById(R.id.chordTypeListBox);
        Button confirmButton = findViewById(R.id.buttonConfirm);

        initializeSpinners();
        initializeChordImageMap();
        loadRandomChordImage();


        ImageButton finishButton = findViewById(R.id.backButton);
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        confirmButton.setOnClickListener(this::onSubmitGuess);
    }

    private void initializeSpinners() {
        rootNotes = new String[]{"C", "D", "E", "F", "G", "A", "B"};
        chordTypes = new String[]{"major", "minor", "augmented", "diminished", "minor augmented", "majorb5", "sus2", "sus4", "unknown"};

        ArrayAdapter<String> rootNoteAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, rootNotes);
        rootNoteAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        rootNoteSpinner.setAdapter(rootNoteAdapter);
        rootNoteSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedRootNote = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        // Set up the adapter and item selected listener for the chordTypeSpinner
        ArrayAdapter<String> chordTypeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, chordTypes);
        chordTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        chordTypeSpinner.setAdapter(chordTypeAdapter);
        chordTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedChordType = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void initializeChordImageMap() {
        chordImageMap = new HashMap<>();
        chordImageMap = new HashMap<>();
        chordImageMap.put("C_major", R.drawable.chord_c_major);
        chordImageMap.put("C_minor", R.drawable.chord_c_minor);
        chordImageMap.put("D_minor", R.drawable.chord_d_minor);
        chordImageMap.put("D_major", R.drawable.chord_d_major);
        chordImageMap.put("E_minor", R.drawable.chord_e_minor);
        chordImageMap.put("E_major", R.drawable.chord_e_major);
        chordImageMap.put("F_major", R.drawable.chord_f_major);
        chordImageMap.put("F_minor", R.drawable.chord_f_minor);
        chordImageMap.put("G_major", R.drawable.chord_g_major);
        chordImageMap.put("G_minor", R.drawable.chord_g_minor);
        chordImageMap.put("A_minor", R.drawable.chord_a_minor);
        chordImageMap.put("A_major", R.drawable.chord_a_major);
        chordKeys = new ArrayList<>(chordImageMap.keySet());

    }

    private void loadRandomChordImage() {
        Random random = new Random();
        String randomChordKey = chordKeys.get(random.nextInt(chordKeys.size()));
        int imageResId = chordImageMap.get(randomChordKey);
        chordImageView.setImageResource(imageResId);
        chordImageView.setTag(randomChordKey);
    }

    public void onSubmitGuess(View view) {
        String displayedChordKey = (String) chordImageView.getTag();
        String userChordGuess = selectedRootNote + "_" + selectedChordType;

        if (userChordGuess.equals(displayedChordKey)) {
            currentScore++;
        } else {
            currentScore = 0;
        }

        scoreTextView.setText("Score: " + currentScore);
        loadRandomChordImage();
    }
}