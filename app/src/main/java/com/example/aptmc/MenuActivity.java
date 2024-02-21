package com.example.aptmc;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MenuActivity extends AppCompatActivity {
    private ImageButton profileButton;
    private Button menuScaleCalculatorButton;
    private Button menuNoteidentificationButton;
    private Button menuChordIdentificationButton;
    private Button menuIntervalIdentificationButton;
    private Button menuNoteSoundButton;
    private Button menuIntervalSoundButton;

    private Context context = this;

    //private String username;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        //ESTO DEbERÍA DE ESTAR FUNCIONANDO PERO NO LO HACE :D (necesita del String username de arriba)
/*
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            String uid = currentUser.getUid();
            DatabaseReference favRef = FirebaseDatabase.getInstance().getReference("usuarios").child(uid).child("username");
            favRef.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()){
                        username = snapshot.getValue(String.class);
                        TextView usernameText = findViewById(R.id.menu_textview_username);
                        usernameText.setText(username);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    username = "Errorrrr";
                }

            });
        } else {
            Toast.makeText(this, "El usuario debe de estar logeado!!", Toast.LENGTH_SHORT).show();
        }
 */

        profileButton = findViewById(R.id.menu_imgbutton_profile);
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Profile.class);
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
                Intent intent = new Intent(context, NoteIdentification.class);
                startActivity(intent);
            }
        });
        menuChordIdentificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ChordIdentification.class);
                startActivity(intent);
            }
        });
        menuIntervalIdentificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, IntervalIdentification.class);
                startActivity(intent);
            }
        });
        menuNoteSoundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NoteSoundActivity.class);
                startActivity(intent);
            }
        });
        menuIntervalSoundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, IntervalSoundActivity.class);
                startActivity(intent);
            }
        });


    }
}
