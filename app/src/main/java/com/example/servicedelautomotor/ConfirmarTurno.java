package com.example.servicedelautomotor;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ConfirmarTurno extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_turno);

        BottomNavigationView bottomNavigationView = findViewById(R.id.barraMenu);
        Menu menu = bottomNavigationView.getMenu();
        menu.findItem(R.id.menu_exit).setVisible(false); // Oculta el ícono de cierre de sesión

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                if (item.getItemId() == R.id.menu_home) {
                    Intent intent = new Intent(ConfirmarTurno.this, Dashboard.class);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });

        // Recupera los datos pasados desde la actividad "SolicitarTurno"
        Intent intent = getIntent();
        String servicio = intent.getStringExtra("servicio");
        String fecha = intent.getStringExtra("fecha");
        String hora = intent.getStringExtra("hora");
        String precio = intent.getStringExtra("precio");

        // Obtiene referencias a los TextView en la actividad "ConfirmarTurno"
        TextView servicioTextView = findViewById(R.id.textServicios1);
        TextView fechaTextView = findViewById(R.id.textFecha1);
        TextView horaTextView = findViewById(R.id.textHora1);
        TextView precioTextView = findViewById(R.id.textPrecio1);

        // Establece los valores en los TextView
        servicioTextView.setText(servicio);
        fechaTextView.setText(fecha);
        horaTextView.setText(hora);
        precioTextView.setText(precio);
    }


    public void botonCancelar(View V) {
        Intent cancelar = new Intent(this, Dashboard.class);
        startActivity(cancelar);
    }
}