package com.example.servicedelautomotor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.servicedelautomotor.coneccionBD.AppDataBase;
import com.example.servicedelautomotor.entidades.Usuario;

public class Register extends AppCompatActivity {
    private EditText usernameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;
    private Button registerButton;

    private AppDataBase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        database = Room.databaseBuilder(getApplicationContext(), AppDataBase.class, "dbServiceAutomotor").build();

        usernameEditText = findViewById(R.id.textUser);
        emailEditText = findViewById(R.id.textCorreo);
        passwordEditText = findViewById(R.id.textContraseña);
        confirmPasswordEditText = findViewById(R.id.textConfirContraseña);
        registerButton = findViewById(R.id.buttonRegistrar);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        // Obtén los datos de los campos de entrada
        String nombreUsuario = usernameEditText.getText().toString();
        String correo = emailEditText.getText().toString();
        String contraseña = passwordEditText.getText().toString();
        String confirmContraseña = confirmPasswordEditText.getText().toString();

        // Realiza validaciones de entrada y lógica de registro

        if (contraseña.equals(confirmContraseña)) {
            // Crea un nuevo objeto User
            Usuario usuario = new Usuario("nombreUsuario", "correo", "contraseña");
            usuario.setNombreUsuario(nombreUsuario);
            usuario.setCorreo(correo);
            usuario.setContraseña(contraseña);

            // Inserta el usuario en la base de datos
            new InsertUserTask().execute(usuario);

            // Limpia los campos de entrada
            usernameEditText.setText("");
            emailEditText.setText("");
            passwordEditText.setText("");
            confirmPasswordEditText.setText("");

            // Muestra un mensaje de registro exitoso
            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
        } else {
            // Muestra un mensaje de error si las contraseñas no coinciden
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
        }
    }

    private class InsertUserTask extends AsyncTask<Usuario, Void, Void> {
        @Override
        protected Void doInBackground(Usuario... usuario) {
            database.daoUsuario().insertarUsuario(usuario[0]);
            return null;
        }
    }
}