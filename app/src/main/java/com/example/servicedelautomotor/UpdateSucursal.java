package com.example.servicedelautomotor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.example.servicedelautomotor.coneccionBD.AppDataBase;
import com.example.servicedelautomotor.entidades.Sucursal;

public class UpdateSucursal extends AppCompatActivity {

    EditText campoNombre, campoTelefono, campoCorreo, campoCalle, campoAltura, campoLocalidad, campoProvincia, campoCP;
    Sucursal sucur;
    long idSucur;

    AppDataBase appDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_sucursal);

        campoNombre = findViewById(R.id.campoNombre);
        campoTelefono = findViewById(R.id.campoTelefono);
        campoCorreo = findViewById(R.id.campoCorreo);
        campoCalle = findViewById(R.id.campoCalle);
        campoAltura = findViewById(R.id.campoAltura);
        campoLocalidad = findViewById(R.id.campoLocalidad);
        campoProvincia = findViewById(R.id.campoProvincia);
        campoCP = findViewById(R.id.campoCP);

        sucur = (Sucursal) getIntent().getSerializableExtra("class");
        //sucur = appDataBase.daoSucursal().obtenerSuscursal(idSucur);

        campoNombre.setText(sucur.getNombre());

    }
}