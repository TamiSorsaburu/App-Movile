package com.example.servicedelautomotor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.servicedelautomotor.coneccionBD.AppDataBase;
import com.example.servicedelautomotor.entidades.Mecanico;

import java.util.List;

public class AgregarMecanicos extends AppCompatActivity {

    EditText campoId, campoNombre, campoApellido, campoTelefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_mecanicos);

        //llamar BD
       // AppDataBase db= AppDataBase.getInstance(this.getApplicationContext());
        //insertando un usuario

        campoId = findViewById(R.id.campoId);
        campoNombre = findViewById(R.id.campoNombre);
        campoApellido = findViewById(R.id.campoApellido);
        campoTelefono = findViewById(R.id.telefono);

       /* AppDataBase appDatabase = Room.databaseBuilder(
                getApplicationContext(),
                AppDataBase.class,
                "dbServiceAutomotor"
        ).allowMainThreadQueries().build();*/

        //appDatabase.daoUsuario().insertarUsuario(new Usuario(1,"usuario@gmail.com","assdd444"));

        //appDatabase.daoMecanico().insertMecanicos(new Mecanico(4,"Nombre","Apellido",56,true));
        //appDatabase.daoMecanico().insertMecanicos(new Mecanico(5,"Nombre5","Apellido5",5,true));
       // appDatabase.daoMecanico().insertMecanicos(new Mecanico(6,"Nombre6","Apellido6",6,true));
        //appDatabase.daoMecanico().insertMecanicos(new Mecanico(7,"Nombre7","Apellido7",56,true));
        //appDatabase.daoMecanico().insertMecanicos(new Mecanico(8,"Nombre8","Apellido8",58,true));


    }

    public void onClick(View view) {
        registrarMecanico();
        Intent intent = new Intent(this, ListadoMecanicos.class);
        startActivity(intent);
    }

    private void registrarMecanico() {

        AppDataBase appDatabase = Room.databaseBuilder(
                getApplicationContext(),
                AppDataBase.class,
                "dbServiceAutomotor"
        ).allowMainThreadQueries().build();
        appDatabase.daoMecanico().insertMecanicos(new Mecanico(Integer.parseInt(campoId.getText().toString()),campoNombre.getText().toString(),campoApellido.getText().toString(), Integer.parseInt(campoTelefono.getText().toString()), true));
    }
}