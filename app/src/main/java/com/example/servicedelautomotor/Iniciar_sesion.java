package com.example.servicedelautomotor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.servicedelautomotor.coneccionBD.AppDataBase;
import com.example.servicedelautomotor.entidades.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Iniciar_sesion extends AppCompatActivity {
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;

    private AppDataBase database;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);

        database = Room.databaseBuilder(getApplicationContext(), AppDataBase.class, "dbServiceAutomotor").build();

        mAuth = FirebaseAuth.getInstance();

        usernameEditText = findViewById(R.id.textUserLogin);
        passwordEditText = findViewById(R.id.userContraseñaLogin);
        loginButton = findViewById(R.id.buttonRecuperarContraseña);

        loginButton.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {

                                               loginUser();
                                           }
                                       }
        );
    }

    private void loginUser() {
        final String correo = usernameEditText.getText().toString();
        final String contraseña = passwordEditText.getText().toString();

        mAuth.signInWithEmailAndPassword(correo, contraseña)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // El inicio de sesión en Firebase fue exitoso
                            // Ahora, busca al usuario en la base de datos local
                            new LoginUserTask().execute(correo, contraseña);
                        } else {
                            // El inicio de sesión en Firebase falló
                            Toast.makeText(Iniciar_sesion.this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Iniciar_sesion.this, "Error al iniciar sesión", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private class LoginUserTask extends AsyncTask<String, Void, Usuario> {
        @Override
        protected Usuario doInBackground(String... credentials) {
            String correo = credentials[0];
            String contraseña = credentials[1];

            // Consulta la base de datos local para verificar las credenciales del usuario
            return database.daoUsuario().obtenerUsuario(correo, contraseña);
        }

        @Override
        protected void onPostExecute(Usuario user) {
            if (user != null && user.getContraseña().equals(passwordEditText.getText().toString())) {
                // Inicio de sesión exitoso en la base de datos local
                // Redirige al usuario a la pantalla principal de la aplicación o realiza otras acciones necesarias
                Toast.makeText(Iniciar_sesion.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Iniciar_sesion.this, Dashboard.class);
                //intent.putExtra("nombreUsuario", user.getNombreUsuario());
                //intent.putExtra("class",user);
                startActivity(intent);
                finish();
            } else {
                // Error de inicio de sesión en la base de datos local
                Toast.makeText(Iniciar_sesion.this, "Credenciales incorrectas o usuario no encontrado", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void textRecuperar(View view) {
        Intent intent = new Intent(this, RecuperarContrasena.class);
        startActivity(intent);
    }

}