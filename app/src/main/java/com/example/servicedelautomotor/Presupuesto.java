package com.example.servicedelautomotor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Presupuesto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presupuesto);
    }
    public void botonConfirmar(View V) {
        Intent confirmar = new Intent(this, SolicitarTurno.class);
        startActivity(confirmar);
    }
    public void botonCancelar(View V) {
        Intent cancelar = new Intent(this, Dashboard.class);
        startActivity(cancelar);
    }
}