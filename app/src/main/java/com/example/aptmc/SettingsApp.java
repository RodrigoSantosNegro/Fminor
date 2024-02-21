package com.example.aptmc;
import android.app.AlertDialog;
import android.content.DialogInterface;
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

import com.google.firebase.auth.EmailAuthProvider;
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
            Intent intent = new Intent(SettingsApp.this, Profile.class);
            startActivity(intent);
        });
    }

    private void configureDeleteUserButton() {
        Button btnDeleteUser = findViewById(R.id.btnDeleteUser);
        btnDeleteUser.setOnClickListener(v -> {
            // Mostrar un cuadro de diálogo de confirmación
            showConfirmationDialog();
        });
    }

    private void showConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmar eliminación");
        builder.setMessage("¿Estás seguro de que quieres eliminar tu cuenta? Esta acción no se puede deshacer.");

        // Agregar el botón (Sí)
        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Enviar un enlace de verificación por correo electrónico al usuario
                sendVerificationEmail();

                // Mostrar un mensaje al usuario indicando que se ha enviado un enlace de verificación
                Toast.makeText(SettingsApp.this, "Se ha enviado un enlace de verificación por correo electrónico. Verifica tu correo antes de eliminar tu cuenta.", Toast.LENGTH_SHORT).show();
            }
        });

        // Agregar el botón (No)
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        // Mostrar el cuadro de diálogo
        builder.show();
    }

    private void sendVerificationEmail() {
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {
            currentUser.sendEmailVerification()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            // Éxito al enviar el correo de verificación
                        } else {
                            // Error al enviar el correo de verificación
                            Toast.makeText(SettingsApp.this, "Error al enviar el correo de verificación", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
}
