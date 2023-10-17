package com.example.servicedelautomotor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Servicios extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicios);
    }

    public void botonCancelar(View V){
        Intent cancelar=new Intent(this, Dashboard.class);
        startActivity(cancelar);
    }

    public void botonSolicitarTurno(View V){
        Intent solicitarT=new Intent(this, SolicitarTurno.class);
        startActivity(solicitarT);
    }



}
