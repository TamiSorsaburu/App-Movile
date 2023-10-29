package com.example.servicedelautomotor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.servicedelautomotor.coneccionBD.AppDataBase;
import com.example.servicedelautomotor.entidades.Usuario;


public class confirmar_contrasena extends AppCompatActivity {
    private EditText editTextCorreo;
    private EditText editTextNuevaContraseña;
    private Button buttonConfirmarContraseña;
    private AppDataBase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_contrasena);

        editTextCorreo = findViewById(R.id.textEmailConfir);
        editTextNuevaContraseña = findViewById(R.id.textContraseñaNueva);
        buttonConfirmarContraseña = findViewById(R.id.btnConfirmContraseña);

        appDatabase = AppDataBase.getInstance(this);

        buttonConfirmarContraseña.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String correo = editTextCorreo.getText().toString();
                String nuevaContraseña = editTextNuevaContraseña.getText().toString();

                // Consulta la base de datos para obtener el usuario por correo
                Usuario usuario = appDatabase.daoUsuario().getUsuarioPorCorreo(correo);

                if (usuario != null) {
                    // Actualiza la contraseña del usuario
                    usuario.setContraseña(nuevaContraseña);
                    appDatabase.daoUsuario().actualizarContraseña(usuario);
                    Toast.makeText(confirmar_contrasena.this, "Contraseña actualizada con éxito", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(confirmar_contrasena.this, Iniciar_sesion.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(confirmar_contrasena.this, "No se actualizó la contraseña", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}