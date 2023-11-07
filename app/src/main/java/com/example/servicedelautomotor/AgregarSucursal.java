package com.example.servicedelautomotor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.servicedelautomotor.coneccionBD.AppDataBase;
import com.example.servicedelautomotor.entidades.Direccion;
import com.example.servicedelautomotor.entidades.Mecanico;
import com.example.servicedelautomotor.entidades.Sucursal;
import com.google.android.material.bottomnavigation.BottomNavigationView;

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
        BottomNavigationView bottomNavigationView = findViewById(R.id.barraMenu);
        Menu menu = bottomNavigationView.getMenu();
        menu.findItem(R.id.menu_exit).setVisible(false); // Oculta el ícono de cierre de sesión

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                if (item.getItemId() == R.id.menu_home) {
                    Intent intent = new Intent(AgregarSucursal.this, Dashboard.class);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });
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