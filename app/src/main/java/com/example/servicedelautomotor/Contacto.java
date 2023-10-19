package com.example.servicedelautomotor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Contacto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        BottomNavigationView bottomNavigationView = findViewById(R.id.barraMenu);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                if (item.getItemId() == R.id.menu_home) {
                    Intent intent = new Intent(Contacto.this, Dashboard.class);
                    startActivity(intent);
                    return true;
                }
                // Agrega más bloques else if para otros elementos de menú si es necesario.
                return false;
            }
        });
    }


    public void botonVolver(View V){
        Intent volver=new Intent(this, Dashboard.class);
        startActivity(volver);
    }
}