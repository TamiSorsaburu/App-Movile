package com.example.servicedelautomotor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Contacto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);
    }

    public void botonVolver(View V){
        Intent volver=new Intent(this, Dashboard.class);
        startActivity(volver);
    }
}