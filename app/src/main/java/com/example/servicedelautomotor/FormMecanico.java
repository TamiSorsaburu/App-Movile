package com.example.servicedelautomotor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class FormMecanico extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_mecanico);
    }
    public void confirmar(View v){
        Intent i = new Intent(this, activity_confirma_ayuda.class);
            startActivity(i);
        }

        public void cancelar(View v){
            Intent i =new Intent(this, Mecanico.class);
            startActivity(i);
        }
    }

