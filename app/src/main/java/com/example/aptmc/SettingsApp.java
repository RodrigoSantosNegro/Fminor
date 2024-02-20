package com.example.aptmc;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;



public class SettingsApp extends AppCompatActivity {

    private Switch themeSwitch;
    private SeekBar volumeSeekBar;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Inicializar vistas y preferencias compartidas
        themeSwitch = findViewById(R.id.themeSwitch);
        volumeSeekBar = findViewById(R.id.volumeSeekBar);
        preferences = getSharedPreferences("AppSettings", MODE_PRIVATE);

        // Configuración del tema
        configureThemeSwitch();

        // Configuración de la barra de volumen
        configureVolumeSeekBar();

        // Configuración del botón de volver
        configureBackButton();
    }

    private void configureThemeSwitch() {
        int themeMode = preferences.getInt("themeMode", AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        themeSwitch.setChecked(themeMode == AppCompatDelegate.MODE_NIGHT_YES);

        themeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                preferences.edit().putInt("themeMode", AppCompatDelegate.MODE_NIGHT_YES).apply();
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                preferences.edit().putInt("themeMode", AppCompatDelegate.MODE_NIGHT_NO).apply();
            }
            recreate();
        });
        preferences.registerOnSharedPreferenceChangeListener((sharedPreferences, key) -> {
            if (key.equals("themeMode")) {
                applyTheme();
                recreate();
            }
        });
    }
    private void applyTheme() {
        int themeMode = preferences.getInt("themeMode", AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        AppCompatDelegate.setDefaultNightMode(themeMode);
    }

    private void configureVolumeSeekBar() {
        int currentVolume = preferences.getInt("volume", 50);
        volumeSeekBar.setProgress(currentVolume);

        volumeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                preferences.edit().putInt("volume", progress).apply();
                // progress para ajusta el volumen de la aplicación
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Método necesario
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Método necesario
            }
        });
    }

    private void configureBackButton() {
        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> {
            finish();
        });
    }
}
