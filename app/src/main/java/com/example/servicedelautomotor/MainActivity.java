package com.example.servicedelautomotor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.servicedelautomotor.coneccionBD.AppDataBase;
import com.example.servicedelautomotor.entidades.Mecanico;
import com.example.servicedelautomotor.entidades.Usuario;

import java.util.List;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //llamar BD
        AppDataBase db= AppDataBase.getInstance(this.getApplicationContext());

        db.daoMecanico().insertMecanicos(new Mecanico(4,"Nombre","Apellido",56,true));
        db.daoMecanico().insertMecanicos(new Mecanico(5,"Nombre5","Apellido5",5,true));
        db.daoMecanico().insertMecanicos(new Mecanico(6,"Nombre6","Apellido6",6,true));
        db.daoMecanico().insertMecanicos(new Mecanico(7,"Nombre7","Apellido7",56,true));
        db.daoMecanico().insertMecanicos(new Mecanico(8,"Nombre8","Apellido8",58,true));

        List<Mecanico> mecanicos = db.daoMecanico().obtenerMecanicos();
        System.out.println("lista de mecanicos");
        for (int i=0;i<mecanicos.size();i++) {

            System.out.println(mecanicos.get(i).nombre);
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