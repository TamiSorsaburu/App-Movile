package com.example.servicedelautomotor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Iniciar_sesion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);
    }

    public void buttonLogin(View view) {
        Intent intent = new Intent(this, Dashboard.class);
        startActivity(intent);
    }
}
