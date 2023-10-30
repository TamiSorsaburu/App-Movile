package com.example.servicedelautomotor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class RecuperarContrasena extends AppCompatActivity {
    private EditText textEmail;
    private Button buttonRecuperarContraseña;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_contrasena);

        textEmail = findViewById(R.id.textEmail); // Asigna el EditText del correo electrónico
        buttonRecuperarContraseña = findViewById(R.id.buttonRecuperarContraseña); // Asigna el botón
        firebaseAuth = FirebaseAuth.getInstance();

        buttonRecuperarContraseña.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = textEmail.getText().toString().trim();
                if (!email.isEmpty()) {
                    // Envía un correo de recuperación de contraseña
                    firebaseAuth.sendPasswordResetEmail(email)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        // El correo de recuperación fue enviado con éxito
                                        Toast.makeText(RecuperarContrasena.this, "Correo de recuperación enviado", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(RecuperarContrasena.this, confirmar_contrasena.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        // Ocurrió un error al enviar el correo de recuperación
                                        Toast.makeText(RecuperarContrasena.this, "Error al enviar el correo de recuperación", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                } else {
                    // El campo de correo electrónico está vacío, muestra un mensaje de error
                    Toast.makeText(RecuperarContrasena.this, "Ingrese un correo electrónico", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
