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

public class NoteSoundActivity extends AppCompatActivity {

    private ImageButton imgbuttonBack;
    private ImageButton imgbuttonPlayPause;
    private TextView textviewScore;
    private MediaPlayer mediaPlayer;
    private int score;
    private int note;

    private Button buttonC;
    private Button buttonCsharp;
    private Button buttonCflat;
    private Button buttonD;
    private Button buttonDsharp;
    private Button buttonDflat;
    private Button buttonE;
    private Button buttonEflat;
    private Button buttonF;
    private Button buttonFsharp;
    private Button buttonG;
    private Button buttonGsharp;
    private Button buttonGflat;
    private Button buttonA;
    private Button buttonAflat;
    private Button buttonB;
    private Button buttonBflat;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_note_sound);
        textviewScore = findViewById(R.id.notesound_textview_scorenumber);
        imgbuttonBack = findViewById(R.id.notesound_button_back);
        imgbuttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mediaPlayer = new MediaPlayer();

        setNoteToPlay();

        imgbuttonPlayPause = findViewById(R.id.notesound_imgbutton_playpause);
        imgbuttonPlayPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    imgbuttonPlayPause.setImageResource(R.drawable.play_button);
                } else {
                    mediaPlayer.start();
                    imgbuttonPlayPause.setImageResource(R.drawable.pause_button);
                }

            }
        });

        buttonA = findViewById(R.id.notesound_button_A);
        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResultOfGuess(R.raw.a);
            }
        });
        buttonB = findViewById(R.id.notesound_button_B);
        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResultOfGuess(R.raw.b);
            }
        });
        buttonC = findViewById(R.id.notesound_button_C);
        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResultOfGuess(R.raw.c);
            }
        });
        buttonD = findViewById(R.id.notesound_button_D);
        buttonD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResultOfGuess(R.raw.d);
            }
        });
        buttonE = findViewById(R.id.notesound_button_E);
        buttonE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResultOfGuess(R.raw.e);
            }
        });
        buttonF = findViewById(R.id.notesound_button_F);
        buttonF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResultOfGuess(R.raw.f);
            }
        });
        buttonG = findViewById(R.id.notesound_button_G);
        buttonG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResultOfGuess(R.raw.g);
            }
        });



    }

    private void ResultOfGuess(int noteResourceID){
        if(note==noteResourceID){
            score++;
        }else{
            score=0;
        }
        textviewScore.setText(String.valueOf(score));
        // Resets media player with another random note
        setNoteToPlay();
    }


    // Sets the media player to play the note
    private void setNoteToPlay(){
        note = getRandomNote();
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaPlayer.setDataSource(NoteSoundActivity.this, Uri.parse("android.resource://" + getPackageName() + "/"+note));
            mediaPlayer.prepareAsync(); // Asynchronous preparation for online resource
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                imgbuttonPlayPause.setImageResource(R.drawable.play_button);
            }
        });
    }

    // Selects a random note from the list of resources
    private static int getRandomNote() {
        // Hardcoded list of notes
        int[] note7List = {R.raw.c, R.raw.d, R.raw.e, R.raw.f, R.raw.g, R.raw.a, R.raw.b};
        // Generate a random index
        int randomIndex = new Random().nextInt(note7List.length);
        // Return the random note
        return note7List[randomIndex];
    }
}
