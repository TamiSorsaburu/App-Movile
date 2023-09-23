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


}
