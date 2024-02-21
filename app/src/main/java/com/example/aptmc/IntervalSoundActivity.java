package com.example.aptmc;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.Random;

public class IntervalSoundActivity extends AppCompatActivity {
    private ImageButton imgbuttonBack;
    private ImageButton imgbuttonPlayPause;
    private TextView textviewScore;
    private MediaPlayer mediaPlayer;
    private int score;
    private int[] notesToPlay;
    private int interval;
    private int i=0;
    private boolean mediaPlayerHasStarted = false;

    private Button button2min;
    private Button button2maj;
    private Button button3min;
    private Button button3maj;
    private Button button4;
    private Button button5dim;
    private Button button5;
    private Button button6min;
    private Button button6maj;
    private Button button7min;
    private Button button7maj;
    private Button button8;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Trial scale
        int[] scale = {R.raw.c, R.raw.d, R.raw.e, R.raw.f, R.raw.g, R.raw.a, R.raw.b};

        setContentView(R.layout.activity_interval_sound);
        // Start with a random music interval
        getRandomInterval();
        // Create a new media player
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        textviewScore = findViewById(R.id.intervalsound_textview_scorenumber);
        imgbuttonBack = findViewById(R.id.intervalsound_button_back);
        imgbuttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        imgbuttonPlayPause = findViewById(R.id.intervalsound_imgbutton_playpause);
        imgbuttonPlayPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mediaPlayerHasStarted) {
                    playNotes(notesToPlay);
                    imgbuttonPlayPause.setImageResource(R.drawable.pause_button);
                } // The following flow control is the same as that in NoteSoundActivity
                else if(mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    imgbuttonPlayPause.setImageResource(R.drawable.play_button);
                } else {
                    mediaPlayer.start();
                    imgbuttonPlayPause.setImageResource(R.drawable.pause_button);
                }
            }
        });

        button2min = findViewById(R.id.intervalsound_button_2min);
        button2min.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {ResultOfGuess(1);}
        });
        button2maj = findViewById(R.id.intervalsound_button_2maj);
        button2maj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {ResultOfGuess(2);}
        });

        button3min = findViewById(R.id.intervalsound_button_3min);
        button3min.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {ResultOfGuess(3);}
        });
        button3maj = findViewById(R.id.intervalsound_button_3maj);
        button3maj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {ResultOfGuess(4);}
        });

        button4 = findViewById(R.id.intervalsound_button_4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {ResultOfGuess(5);}
        });
        button5dim = findViewById(R.id.intervalsound_button_5dim);
        button5dim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {ResultOfGuess(6);}
        });
        button5 = findViewById(R.id.intervalsound_button_5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {ResultOfGuess(7);}
        });

        button6min = findViewById(R.id.intervalsound_button_6min);
        button6min.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {ResultOfGuess(8);}
        });
        button6maj = findViewById(R.id.intervalsound_button_6maj);
        button6maj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {ResultOfGuess(9);}
        });

        button7min = findViewById(R.id.intervalsound_button_7min);
        button7min.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {ResultOfGuess(10);}
        });
        button7maj = findViewById(R.id.intervalsound_button_7maj);
        button7maj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {ResultOfGuess(11);}
        });
        button8 = findViewById(R.id.intervalsound_button_8);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {ResultOfGuess(12);}
        });

    }

    // Recursive method to play a sequence of sounds (notes on the piano) defined by resource number with a single media player
    private void playNotes(int[] notes){
        mediaPlayerHasStarted = true; // Boolean is reset to false when a new interval for the player is set
        mediaPlayer.reset();
        try { //Set as data source the corresponding sound (note) in the array
            mediaPlayer.setDataSource(IntervalSoundActivity.this, Uri.parse("android.resource://" + getPackageName() + "/"+notes[i]));
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    // Start playback after preparation is completed
                    mediaPlayer.start();
                }
            });

            mediaPlayer.prepareAsync(); // Asynchronous preparation for online resource
        } catch (IOException e) {
            e.printStackTrace();
        }
        // When a sound of the given sequence finishes playing, media player starts playing the next one. If that was the last one, it stops and resets the button
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                i++; //Next
                if(i>= notes.length){ //Sound sequence finished playing
                    imgbuttonPlayPause.setImageResource(R.drawable.play_button);
                    i=0;
                    mediaPlayerHasStarted = false;
                }else{ //Next sound in sequence is played
                    playNotes(notes);
                }
            }
        });
    }
    // Method that handles the guess. Called when buttons are pressed
    private void ResultOfGuess(int numberOfSemitonesOfGuess){
        // Compares the interval played with the one corresponding to this button, expressed in number of semitones
        mediaPlayer.reset();
        imgbuttonPlayPause.setImageResource(R.drawable.play_button);
        i=0;
        if(interval==numberOfSemitonesOfGuess){score++; //Correct guess increases score (+1). Incorrect guess resets it to 0
        }else{score=0;
            CorrectIntervalDialogFragment fragment = CorrectIntervalDialogFragment.newInstance(interval);
            fragment.show(getSupportFragmentManager(), null);}
        textviewScore.setText(String.valueOf(score));
        // Sets another random interval (note sequence) to play, updating notesToPlay[] and interval
        getRandomInterval();
    }

    // Selects a random interval, using notes from the list of resources
    private void getRandomInterval() {
        // Hardcoded list of notes
        int[] note7List = {R.raw.c, R.raw.d, R.raw.e, R.raw.f, R.raw.g, R.raw.a, R.raw.b};
        int[] absRelationship = {0, 2, 4, 5, 7, 9, 11};
        // Generate 2 random indexes
        int randomIndex1 = new Random().nextInt(note7List.length);
        int randomIndex2 = new Random().nextInt(note7List.length);
        // Calculates interval in semitones. Stores it in instance of class variable
        interval = Math.abs(absRelationship[randomIndex2] - absRelationship[randomIndex1]);
        // Return the random pair of notes
        int[] interval = new int[2];
        interval[0] = note7List[randomIndex1];
        interval[1] = note7List[randomIndex2];
        // Stores interval in instance of class variable
        notesToPlay = interval;
        // When a new interval is set, it is assumed the media player hasn't played it yet: it's just been set
        mediaPlayerHasStarted = false;
    }
}
