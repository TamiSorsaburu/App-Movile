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
import com.example.servicedelautomotor.entidades.Sucursal;

public class UpdateSucursal extends AppCompatActivity {

    EditText campoNombre, campoTelefono, campoCorreo, campoCalle, campoAltura, campoLocalidad, campoProvincia, campoCP;
    Sucursal sucur;


    AppDataBase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_sucursal);

        appDatabase = Room.databaseBuilder(
                getApplicationContext(),
                AppDataBase.class,
                "dbServiceAutomotor"
        ).allowMainThreadQueries().build();

        campoNombre = findViewById(R.id.campoNombre);
        campoTelefono = findViewById(R.id.campoTelefono);
        campoCorreo = findViewById(R.id.campoCorreo);
        campoCalle = findViewById(R.id.campoCalle);
        campoAltura = findViewById(R.id.campoAltura);
        campoLocalidad = findViewById(R.id.campoLocalidad);
        campoProvincia = findViewById(R.id.campoProvincia);
        campoCP = findViewById(R.id.campoCP);

        sucur = (Sucursal) getIntent().getSerializableExtra("class");


        campoNombre.setText(sucur.getNombre());
        campoTelefono.setText(Integer.toString(sucur.telefono));
        campoCorreo.setText(sucur.correo);
        campoCalle.setText(sucur.direccion.calle);
        campoAltura.setText(sucur.direccion.altura);
        campoLocalidad.setText(sucur.direccion.localidad);
        campoProvincia.setText(sucur.direccion.provincia);
        campoCP.setText(Integer.toString(sucur.direccion.codigoPostal));



    }

    public void onClick(View view) {
        editarSucursal();
        Intent intent = new Intent(this, ListadoSucursales.class);
        startActivity(intent);
    }

    private void editarSucursal() {

        appDatabase.daoSucursal().actualizarSucursal(sucur.idSucursal,campoNombre.getText().toString(),Integer.parseInt(campoTelefono.getText().toString()),campoCorreo.getText().toString(),campoCalle.getText().toString(),campoAltura.getText().toString(),campoLocalidad.getText().toString(),campoProvincia.getText().toString(),Integer.parseInt(campoCP.getText().toString()));
        Toast.makeText(UpdateSucursal.this, "Sucursal " + campoNombre.getText().toString() + " editada", Toast.LENGTH_SHORT).show();
    }


}