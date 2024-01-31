package com.example.aptmc;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class IntervalIdentification extends AppCompatActivity {
    private ImageView sheetImageView;
    private int currentScore = 0;
    private TextView scoreTextView;
    private Map<String, Integer> intervalImageMap; // Maps interval names to drawable IDs

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identification_interval);

        sheetImageView = findViewById(R.id.sheetDisplay);
        scoreTextView = findViewById(R.id.scoreCounter);

        ImageButton finishButton = findViewById(R.id.backButton);
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        initializeIntervalImageMap();
        setUpIntervalButtons();
        loadRandomSheet();
    }

    // Call this method to load a random sheet
    private void loadRandomSheet() {
        // Choose a random interval from the map keys
        List<String> intervals = new ArrayList<>(intervalImageMap.keySet());
        String randomInterval = intervals.get(new Random().nextInt(intervals.size()));

        // Update the ImageView with the random interval's image
        sheetImageView.setImageResource(intervalImageMap.get(randomInterval));

        // Store the chosen interval in the ImageView's tag for later reference
        sheetImageView.setTag(randomInterval);
    }

    // This method will be called when any interval button is clicked
    public void onIntervalButtonClick(View view) {
        String clickedInterval = view.getTag().toString();
        String displayedInterval = sheetImageView.getTag().toString();

        if (clickedInterval.equals(displayedInterval)) {
            currentScore++;
        } else {
            currentScore = 0;
        }

        // Update the score display
        scoreTextView.setText("Score: " + currentScore);

        // Load a new random sheet for the next round
        loadRandomSheet();
    }

    private void initializeIntervalImageMap() {
        // Initialize the map with interval names and corresponding image drawable IDs
        intervalImageMap = new HashMap<>();
        intervalImageMap.put("Minor Second", R.drawable.interval_minor_second);
        intervalImageMap.put("Major Second", R.drawable.interval_major_second);
        intervalImageMap.put("Minor Third", R.drawable.interval_minor_thrid);
        intervalImageMap.put("Perfect Fourth", R.drawable.interval_perfect_fourth);
        intervalImageMap.put("Tritone", R.drawable.interval_tritone);
        intervalImageMap.put("Perfect Fifth", R.drawable.interval_perfect_fifth);
        intervalImageMap.put("Minor Sixth", R.drawable.interval_minor_sixth);
        intervalImageMap.put("Major Sixth", R.drawable.interval_major_sixth);
        intervalImageMap.put("Minor Seventh", R.drawable.interval_minor_seventh);
        intervalImageMap.put("Major Seventh", R.drawable.interval_major_seventh);
        intervalImageMap.put("Octave", R.drawable.interval_octave);
    }

    private void setUpIntervalButtons() {
        int[] buttonIds = new int[]{
                // Replace these IDs with the actual button IDs from your layout
                R.id.buttonMinorSecond, R.id.buttonMajorSecond, R.id.buttonMinorThird,
                R.id.buttonMajorThird, R.id.buttonPerfectFourth, R.id.buttonTritone, R.id.buttonPerfectFifth,
                R.id.buttonMinorSixth, R.id.buttonMajorSixth, R.id.buttonMinorSeventh, R.id.buttonMajorSeventh,
                R.id.buttonOctave
        };

        for (int buttonId : buttonIds) {
            Button intervalButton = findViewById(buttonId);
            intervalButton.setOnClickListener(this::onIntervalButtonClick);
            // Setting the tag as the interval associated with the button
            intervalButton.setTag(intervalButton.getText().toString());
        }
    }
}