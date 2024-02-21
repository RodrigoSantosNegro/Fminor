package com.example.aptmc;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class InicioSesionActivity extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Inicializar FirebaseApp
        FirebaseApp.initializeApp(this);

        // Inicializar FirebaseAuth después de inicializar FirebaseApp
        mAuth = FirebaseAuth.getInstance();

        editTextEmail = findViewById(R.id.editTextEmailLogin);
        editTextPassword = findViewById(R.id.editTextPasswordLogin);

        Button buttonLogin = findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(v -> iniciarSesion());

        // Agregar OnClickListener al botón de registro
        Button buttonRegister = findViewById(R.id.buttonRegister);
        buttonRegister.setOnClickListener(v -> abrirActividadRegistro());
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Verificar si el usuario ya ha iniciado sesión
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            // El usuario ya está autenticado, navegar a la pantalla principal
            abrirPantallaPrincipal();
        }
    }

    private void iniciarSesion() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        System.out.println(mAuth.getCurrentUser()); // Validaciones de los campos...

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Iniciar sesión exitoso, navegar a la pantalla principal
                        abrirPantallaPrincipal();
                    } else {
                        Toast.makeText(InicioSesionActivity.this, "Error al iniciar sesión", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void abrirActividadRegistro() {
        Intent intent = new Intent(InicioSesionActivity.this, RegistroActivity.class);
        startActivity(intent);
    }

    private void abrirPantallaPrincipal() {
        Intent intent = new Intent(InicioSesionActivity.this, MenuActivity.class);
        startActivity(intent);
        finish(); // Cierra la actividad actual
    }
}
