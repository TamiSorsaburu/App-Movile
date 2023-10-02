package com.example.servicedelautomotor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ConfirmarTurno extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_turno);
    }
    public void botonCancelar(View V) {
        Intent cancelar = new Intent(this, Dashboard.class);
        startActivity(cancelar);
    }
}