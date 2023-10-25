package com.example.servicedelautomotor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.servicedelautomotor.coneccionBD.AppDataBase;
import com.example.servicedelautomotor.entidades.Direccion;
import com.example.servicedelautomotor.entidades.Mecanico;
import com.example.servicedelautomotor.entidades.Sucursal;

public class AgregarSucursal extends AppCompatActivity {

    EditText campoNombre, campoTelefono, campoCorreo, campoCalle, campoAltura, campoLocalidad, campoProvincia, campoCP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_sucursal);

        campoNombre = findViewById(R.id.campoNombre);
        campoTelefono = findViewById(R.id.campoTelefono);
        campoCorreo = findViewById(R.id.campoCorreo);
        campoCalle = findViewById(R.id.campoCalle);
        campoAltura = findViewById(R.id.campoAltura);
        campoLocalidad = findViewById(R.id.campoLocalidad);
        campoProvincia = findViewById(R.id.campoProvincia);
        campoCP = findViewById(R.id.campoCP);
    }

    public void onClick(View view) {
        registrarSucursal();
        Intent intent = new Intent(this, ListadoSucursales.class);
        startActivity(intent);
    }

    private void registrarSucursal() {

        AppDataBase appDatabase = Room.databaseBuilder(
                getApplicationContext(),
                AppDataBase.class,
                "dbServiceAutomotor"
        ).allowMainThreadQueries().build();
        Direccion direccion = new Direccion(campoCalle.getText().toString(),campoAltura.getText().toString(),campoLocalidad.getText().toString(),campoProvincia.getText().toString(),Integer.parseInt(campoCP.getText().toString()));
        appDatabase.daoDireccion().insertarDireccion(direccion);
        appDatabase.daoSucursal().insertarSucursal(new Sucursal(campoNombre.getText().toString(),Integer.parseInt(campoTelefono.getText().toString()),campoCorreo.getText().toString(),direccion));

        Toast.makeText(AgregarSucursal.this, "Sucursal " + campoNombre.getText().toString() + " agregada", Toast.LENGTH_SHORT).show();
    }

    public void btnListar(View view) {
        Intent intent = new Intent(this, ListadoSucursales.class);
        startActivity(intent);
    }
}