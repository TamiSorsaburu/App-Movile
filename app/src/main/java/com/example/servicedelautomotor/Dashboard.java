package com.example.servicedelautomotor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Dashboard extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    }

    //metodo para botones
    public void botonMecanico(View V) {
        Intent mecanico = new Intent(this, Mecanico.class);
        startActivity(mecanico);
    }

    public void botonSiniestro(View V) {
        Intent siniestro = new Intent(this, SelectSiniestro.class);
        startActivity(siniestro);
    }

    public void botonGrua(View V) {
        Intent grua = new Intent(this, SiniestrosActivity.class);
        startActivity(grua);
    }

    public void botonMisTurnos(View v) {
        Intent miTurno = new Intent(this, MisTurnos.class);
        startActivity(miTurno);
    }

    public void botonServicio(View V) {
        Intent servicio = new Intent(this, Servicios.class);
        startActivity(servicio);

    }

    public void botonMiAuto(View V) {
        Intent auto = new Intent(this, InformacionAuto.class);
        startActivity(auto);
    }

    public void botonContacto(View V){
        Intent contacto=new Intent(this, Contacto.class);
        startActivity(contacto);
    }

    public void botonPerfil(View V){
        Intent perfil=new Intent(this,InformacionPersonal.class);
        startActivity(perfil);
    }


}



     /*
    public void botonPresupuesto(View V){
        Intent presupuesto=new Intent(this, SiniestrosActivity.class);
        startActivity(presupuesto);
    }*/


