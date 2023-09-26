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
    public void botonMecanico(View V){
        Intent mecanico=new Intent(this, Mecanico.class);
        startActivity(mecanico);
    }

    public void botonSiniestro(View V){
        Intent siniestro=new Intent(this, SiniestrosActivity.class);
        startActivity(siniestro);
    }

    /*
    public void botonServicio(View V){
        Intent servicio=new Intent(this, Servicio.class);
        startActivity(servicio);
    }*/

     /*
    public void botonGrua(View V){
        Intent grua=new Intent(this, Grua.class);
        startActivity(grua);
    }*/

     /*
    public void botonMiAuto(View V){
        Intent auto=new Intent(this, MiAuto.class);
        startActivity(auto);
    }*/

     /*
    public void botonContacto(View V){
        Intent contacto=new Intent(this, Contacto.class);
        startActivity(contacto);
    }*/

     /*
    public void botonConfiguracion(View V){
        Intent configuracion=new Intent(this, SiniestrosActivity.class);
        startActivity(configuracion);
    }*/

}
