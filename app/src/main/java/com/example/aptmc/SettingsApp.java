package com.example.aptmc;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SettingsApp extends AppCompatActivity {

    private Switch themeSwitch;
    private SeekBar volumeSeekBar;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Inicializar vistas y preferencias compartidas
        themeSwitch = findViewById(R.id.themeSwitch);
        volumeSeekBar = findViewById(R.id.volumeSeekBar);
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
            recreate();
        });
    }

    private boolean isDarkThemeEnabled() {
        return getSharedPreferences("AppSettings", MODE_PRIVATE)
                .getBoolean("isDarkThemeEnabled", false);
    }

    private void setDarkThemeEnabled(boolean isEnabled) {
        getSharedPreferences("AppSettings", MODE_PRIVATE)
                .edit()
                .putBoolean("isDarkThemeEnabled", isEnabled)
                .apply();

        AppCompatDelegate.setDefaultNightMode(isEnabled
                ? AppCompatDelegate.MODE_NIGHT_YES
                : AppCompatDelegate.MODE_NIGHT_NO);
    }

    private void configureVolumeSeekBar() {
        int currentVolume = getSharedPreferences("AppSettings", MODE_PRIVATE)
                .getInt("volume", 50);
        volumeSeekBar.setProgress(currentVolume);

        volumeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                getSharedPreferences("AppSettings", MODE_PRIVATE)
                        .edit()
                        .putInt("volume", progress)
                        .apply();
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
            // Mostrar  diálogo para la confirmación
            showConfirmationDialog();
        });
    }

    private void showConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmar eliminación");
        builder.setMessage("¿Estás seguro de que quieres eliminar tu cuenta?");

        // Agregar el botón (Sí)
        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Solicitar contraseña antes de eliminar la cuenta
                showPasswordInputDialog();
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

    private void showPasswordInputDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Ingrese su contraseña");

        // Crear un EditText para que el usuario ingrese la contraseña
        final EditText passwordInput = new EditText(this);
        passwordInput.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        builder.setView(passwordInput);

        // Agregar botones al cuadro de diálogo
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Obtener contraseña
                String password = passwordInput.getText().toString();

                // Eliminar la cuenta del usuario
                deleteCurrentUserAccount(password);
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        // Mostrar el cuadro de diálogo
        builder.show();
    }

    private void deleteCurrentUserAccount(String password) {
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();

        if (currentUser != null) {
            AuthCredential credential = EmailAuthProvider.getCredential(currentUser.getEmail(), password);

            currentUser.reauthenticate(credential)
                    .addOnCompleteListener(reauthTask -> {
                        if (reauthTask.isSuccessful()) {
                            currentUser.delete()
                                    .addOnCompleteListener(deleteTask -> {
                                        if (deleteTask.isSuccessful()) {
                                            // Éxito al eliminar el usuario
                                            Toast.makeText(SettingsApp.this, "Cuenta eliminada con éxito", Toast.LENGTH_SHORT).show();

                                            // Redirigir a la pantalla de inicio de sesión
                                            Intent intent = new Intent(SettingsApp.this, InicioSesionActivity.class);
                                            startActivity(intent);
                                            finish(); // Finalizar la actividad actual
                                        } else {
                                            // Error al eliminar el usuario
                                            Toast.makeText(SettingsApp.this, "Error al eliminar la cuenta: " + deleteTask.getException(), Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        } else {
                            // Error en la reautenticación
                            Toast.makeText(SettingsApp.this, "Error en la reautenticación: " + reauthTask.getException(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
}
