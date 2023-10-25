package com.example.servicedelautomotor;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MisTurnos extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_turnos);
        BottomNavigationView bottomNavigationView = findViewById(R.id.barraMenu);
        Menu menu = bottomNavigationView.getMenu();
        menu.findItem(R.id.menu_exit).setVisible(false); // Oculta el ícono de cierre de sesión

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                if (item.getItemId() == R.id.menu_home) {
                    Intent intent = new Intent(MisTurnos.this, Dashboard.class);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });
    }

    public void botonVolver(View V){
        Intent volver=new Intent(this, Dashboard.class);
        startActivity(volver);
    }
}
