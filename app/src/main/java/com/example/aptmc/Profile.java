package com.example.aptmc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Profile extends AppCompatActivity {
    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    // Obtener el usuario actualmente autenticado
    FirebaseUser currentUser = mAuth.getCurrentUser();
    private ImageButton profileBackButton;
    private ImageButton profileSettingsButton;
    private ImageView profileUserImageView;
    private TextView profileUserEmail;
    private TextView logOutTextView;
    private RecyclerView recyclerView;
    private ProfileAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        this.recyclerView = findViewById(R.id.favScaleRecView);
        this.recyclerView.setLayoutManager( new LinearLayoutManager(this));
        adapter = new ProfileAdapter();
        recyclerView.setAdapter(adapter);


        profileBackButton = findViewById(R.id.profileBackButton);
        profileBackButton.setBackground(null);
        profileBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, MainTest.class);
                startActivity(intent);
            }
        });

        profileSettingsButton = findViewById(R.id.profileSettingsButton);
        profileSettingsButton.setBackground(null);
        profileSettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, SettingsApp.class);
                startActivity(intent);
            }
        });

        profileUserImageView = findViewById(R.id.profileUserImageView);
        profileUserImageView.setBackground(null);

        profileUserEmail = findViewById(R.id.profileUserEmail);
        if (currentUser != null) {

            String correoUsuario = currentUser.getEmail();

            profileUserEmail.setText(correoUsuario);

        } else {
            Toast.makeText(this, "No hay ninguna sesión iniciada", Toast.LENGTH_SHORT).show();
        }

        logOutTextView = findViewById(R.id.logOutTextView);
        logOutTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Toast.makeText(Profile.this, "Log out succesfull", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Profile.this, InicioSesionActivity.class);
            }
        });

    }
}
