package com.example.servicedelautomotor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.servicedelautomotor.crud.AgregarCliente;
import com.example.servicedelautomotor.crud.AgregarMecanico;

public class MenuAdmin extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_admin);
    }

    public void botonVolver(View V) {
        Intent i = new Intent(this, Dashboard.class);
        startActivity(i);
    }

    public void crudMecanico(View V) {
        Intent i = new Intent(this, AgregarMecanico.class);
        startActivity(i);
    }

    public void crudSucursal(View V) {
        Intent i = new Intent(this, AgregarSucursal.class);
        startActivity(i);
    }

    public void crudCliente(View V) {
        Intent i = new Intent(this, AgregarCliente.class);
        startActivity(i);
    }

}
