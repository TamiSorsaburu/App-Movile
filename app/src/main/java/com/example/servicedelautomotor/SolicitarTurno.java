package com.example.servicedelautomotor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SolicitarTurno extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitar_turno);
    }
    public void botonConfirmar(View V) {
        Intent confirmar = new Intent(this, ConfirmarTurno.class);
        startActivity(confirmar);
    }
    public void botonCancelar(View V) {
        Intent cancelar = new Intent(this, Presupuesto.class);
        startActivity(cancelar);
    }
}