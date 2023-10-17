package com.example.servicedelautomotor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MisTurnos extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_turnos);
    }

    public void botonVolver(View V){
        Intent volver=new Intent(this, Dashboard.class);
        startActivity(volver);
    }
}
