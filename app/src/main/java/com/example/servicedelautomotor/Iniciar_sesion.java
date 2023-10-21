package com.example.servicedelautomotor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.servicedelautomotor.coneccionBD.AppDataBase;
import com.example.servicedelautomotor.entidades.Usuario;

public class Iniciar_sesion extends AppCompatActivity {
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private AppDataBase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);

        database = Room.databaseBuilder(getApplicationContext(), AppDataBase.class, "dbServiceAutomotor").build();

        usernameEditText = findViewById(R.id.textUserLogin);
        passwordEditText = findViewById(R.id.userContraseñaLogin);
        loginButton = findViewById(R.id.buttonRegister);

        loginButton.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {
                                               loginUser();
                                           }
                                       }
        );
    }

    private void loginUser() {
        String correo = usernameEditText.getText().toString();
        String contraseña = passwordEditText.getText().toString();

        // Realiza la verificación de inicio de sesión
        new LoginUserTask().execute(correo, contraseña);
    }

    private class LoginUserTask extends AsyncTask<String, Void, Usuario> {
        @Override
        protected Usuario doInBackground(String... credentials) {
            String correo = credentials[0];
            String contraseña = credentials[1];

            // Consulta la base de datos para verificar las credenciales del usuario
            return database.daoUsuario().obtenerUsuario(correo, contraseña);
        }

        @Override
        protected void onPostExecute(Usuario user) {
            if (user != null && user.getContraseña().equals(passwordEditText.getText().toString())) {
                // Inicio de sesión exitoso
                // Redirige al usuario a la pantalla principal de la aplicación o realiza otras acciones necesarias
                Toast.makeText(Iniciar_sesion.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Iniciar_sesion.this, Dashboard.class);
                intent.putExtra("nombreUsuario", user.getNombreUsuario());
                startActivity(intent);
                finish();
            } else {
                // Error de inicio de sesión
                Toast.makeText(Iniciar_sesion.this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void textRecuperarContraseña(View view) {
        Intent intent = new Intent(this, RecuperarContrasena.class);
        startActivity(intent);
    }
}
