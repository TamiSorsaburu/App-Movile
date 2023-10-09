package com.example.servicedelautomotor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MecanicoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mecanico);
    }

    public void botonConfirmar(View V){
        Intent confirmar=new Intent(this, FormMecanico.class);
        startActivity(confirmar);
    }

    public void botonCancelar(View V){
        Intent cancelar=new Intent(this, Dashboard.class);
        startActivity(cancelar);
    }
}
