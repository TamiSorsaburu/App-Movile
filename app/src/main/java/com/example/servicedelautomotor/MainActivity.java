package com.example.servicedelautomotor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.servicedelautomotor.coneccionBD.AppDataBase;
import com.example.servicedelautomotor.entidades.Usuario;



public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //llamar BD
        AppDataBase db= AppDataBase.getInstance(this.getApplicationContext());
        //insertando un usuario
        db.daoUsuario().insertarUsuario(new Usuario(1,"usuario@gmail.com","assdd444"));

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