package com.example.aptmc;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SettingsApp extends AppCompatActivity {

    private Switch themeSwitch;
    private SeekBar volumeSeekBar;
    private SharedPreferences preferences;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Inicializar vistas y preferencias compartidas
        themeSwitch = findViewById(R.id.themeSwitch);
        volumeSeekBar = findViewById(R.id.volumeSeekBar);
        preferences = getSharedPreferences("AppSettings", MODE_PRIVATE);
        firebaseAuth = FirebaseAuth.getInstance();

        // Configuración del tema
        configureThemeSwitch();

        // Configuración de la barra de volumen
        configureVolumeSeekBar();

        // Configuración del botón de volver
        configureBackButton();

        // Configuración del botón de eliminar usuario
        configureDeleteUserButton();
    }

    private void configureThemeSwitch() {


        themeSwitch.setChecked(isDarkThemeEnabled());

        themeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Modificar el tema cuando cambia el estado del interruptor
            setDarkThemeEnabled(isChecked);
            recreate(); // Forzar la recreación de la actividad para aplicar el nuevo tema
        });

    }
    private boolean isDarkThemeEnabled() {
        // Obtener el estado del tema oscuro desde las preferencias compartidas
        return getSharedPreferences("AppSettings", MODE_PRIVATE)
                .getBoolean("isDarkThemeEnabled", false);
    }
    private void setDarkThemeEnabled(boolean isEnabled) {
        // Guardar el estado del tema oscuro en las preferencias compartidas
        getSharedPreferences("AppSettings", MODE_PRIVATE)
                .edit()
                .putBoolean("isDarkThemeEnabled", isEnabled)
                .apply();

        // Establecer el tema de la aplicación según el estado
        AppCompatDelegate.setDefaultNightMode(isEnabled
                ? AppCompatDelegate.MODE_NIGHT_YES
                : AppCompatDelegate.MODE_NIGHT_NO);
    }

    private void configureVolumeSeekBar() {
        int currentVolume = preferences.getInt("volume", 50);
        volumeSeekBar.setProgress(currentVolume);

        volumeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                preferences.edit().putInt("volume", progress).apply();
                // progress para ajustar el volumen de la aplicación
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
            Intent intent = new Intent(SettingsApp.this, InicioSesionActivity.class);
            startActivity(intent);
        });
    }

    private void configureDeleteUserButton() {
        Button btnDeleteUser = findViewById(R.id.btnDeleteUser);
        btnDeleteUser.setOnClickListener(v -> {
            // Obtener el usuario actualmente autenticado
            FirebaseUser currentUser = firebaseAuth.getCurrentUser();

            // Verificar si el usuario está autenticado
            if (currentUser != null) {
                // Eliminar el usuario actual
                currentUser.delete()
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                // El usuario se eliminó exitosamente
                                Toast.makeText(SettingsApp.this, "Usuario eliminado exitosamente", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SettingsApp.this, InicioSesionActivity.class);
                                finish(); // Cerrar la actividad después de eliminar el usuario
                                startActivity(intent);
                            } else {
                                // Error al eliminar el usuario
                                Toast.makeText(SettingsApp.this, "Error al eliminar el usuario", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}
