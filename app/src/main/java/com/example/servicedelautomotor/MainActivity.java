package com.example.servicedelautomotor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.servicedelautomotor.coneccionBD.AppDataBase;
import com.example.servicedelautomotor.entidades.Mecanico;
import com.example.servicedelautomotor.entidades.Usuario;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser() != null) {
            startActivity(new Intent(MainActivity.this, Dashboard.class));
            finish();
        }

    }

    public void buttonLogin(View view) {
        Intent intent = new Intent(this, Iniciar_sesion.class);
        startActivity(intent);
    }

    public void textRegister(View view) {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

}