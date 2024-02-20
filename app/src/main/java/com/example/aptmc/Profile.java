package com.example.aptmc;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Profile extends AppCompatActivity {
    private ImageButton profileBackButton;
    private ImageButton profileSettingsButton;
    private ImageView profileUserImageView;
    private TextView profileUserName;
    private TextView profileUserEmail;
    private RecyclerView recyclerView;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        this.recyclerView = findViewById(R.id.favScaleRecView);
        this.recyclerView.setLayoutManager( new LinearLayoutManager(this));
        profileBackButton = findViewById(R.id.profileBackButton);
        profileSettingsButton = findViewById(R.id.profileSettingsButton);
        profileUserImageView = findViewById(R.id.profileUserImageView);
        profileUserName = findViewById(R.id.profileUserName);
        profileUserEmail = findViewById(R.id.profileUserEmail);


    }
}
