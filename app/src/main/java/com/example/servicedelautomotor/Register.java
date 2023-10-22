package com.example.servicedelautomotor;

import androidx.annotation.NonNull;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Register extends AppCompatActivity {
    private EditText usernameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;
    private Button registerButton;

    private AppDataBase database;

    FirebaseAuth mAuth;
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

        mAuth = FirebaseAuth.getInstance();

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        final String nombreUsuario = usernameEditText.getText().toString().trim();
        final String correo = emailEditText.getText().toString().trim();
        final String contraseña = passwordEditText.getText().toString().trim();
        final String confirmContraseña = confirmPasswordEditText.getText().toString().trim();

        if (correo.isEmpty() || contraseña.isEmpty() || confirmContraseña.isEmpty()) {
            Toast.makeText(this, "Ingrese datos !!", Toast.LENGTH_SHORT).show();
        } else if (emailValido(correo)) {
            if (contraseña.equals(confirmContraseña)) {
                if (confirmContraseña.length() < 6) {
                    Toast.makeText(this, "La contraseña debe tener más de 6 caracteres", Toast.LENGTH_SHORT).show();
                } else {
                    // Aquí verificamos si el correo ya está en la base de datos
                    new CheckEmailExistenceTask().execute(correo);
                }
            } else {
                Toast.makeText(this, "Los campos no coinciden", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Email inválido", Toast.LENGTH_SHORT).show();
        }
    }

    private class CheckEmailExistenceTask extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... params) {
            // Verificar si el correo ya existe en la base de datos
            String correo = params[0];
            Usuario usuarioExistente = database.daoUsuario().getUsuarioPorCorreo(correo);
            return usuarioExistente != null;
        }

        @Override
        protected void onPostExecute(Boolean emailExists) {
            final String nombreUsuario = usernameEditText.getText().toString().trim();
            final String correo = emailEditText.getText().toString().trim();
            final String contraseña = passwordEditText.getText().toString().trim();
            if (emailExists) {
                Toast.makeText(Register.this, "El correo electrónico ya está registrado", Toast.LENGTH_SHORT).show();
            } else {
                // Continuar con el registro si el correo no existe
                mAuth.createUserWithEmailAndPassword(correo, contraseña)
                        .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Usuario usuario = new Usuario(nombreUsuario, correo, contraseña);

                                    new InsertUserTask().execute(usuario);

                                    usernameEditText.setText("");
                                    emailEditText.setText("");
                                    passwordEditText.setText("");
                                    confirmPasswordEditText.setText("");

                                    Toast.makeText(Register.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Register.this, Iniciar_sesion.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(Register.this, "Error al registrar", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        }
    }

    private class InsertUserTask extends AsyncTask<Usuario, Void, Void> {
        @Override
        protected Void doInBackground(Usuario... usuario) {
            database.daoUsuario().insertarUsuario(usuario[0]);
            return null;
        }
    }

    private boolean emailValido(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}