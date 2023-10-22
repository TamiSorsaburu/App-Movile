package com.example.servicedelautomotor;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class Dashboard extends AppCompatActivity {
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        String name = getIntent().getStringExtra("nombreUsuario");
        TextView nameTextView = findViewById(R.id.name);
        nameTextView.setText(name);

        BottomNavigationView bottomNavigationView = findViewById(R.id.barraMenu);

        mAuth = FirebaseAuth.getInstance();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                if(item.getItemId() == R.id.menu_exit) {
                    mAuth.signOut();
                    Intent intent = new Intent(Dashboard.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    return true;
                }
                return false;
            }
        });
    }

    //metodo para botones
    public void botonMecanico(View V) {
        Intent mecanico = new Intent(this, MecanicoActivity.class);
        startActivity(mecanico);
    }

    public void botonSiniestro(View V) {
        Intent siniestro = new Intent(this, SelectSiniestro.class);
        startActivity(siniestro);
    }

    public void botonGrua(View V) {
        Intent grua = new Intent(this, SiniestrosActivity.class);
        startActivity(grua);
    }

    public void botonMisTurnos(View v) {
        Intent miTurno = new Intent(this, MisTurnos.class);
        startActivity(miTurno);
    }

    public void botonServicio(View V) {
        Intent servicio = new Intent(this, Servicios.class);
        startActivity(servicio);

    }

    public void botonMiAuto(View V) {
        Intent auto = new Intent(this, InformacionAuto.class);
        startActivity(auto);
    }

    public void botonContacto(View V){
        Intent contacto=new Intent(this, Contacto.class);
        startActivity(contacto);
    }

    public void botonPerfil(View V){
        Intent perfil=new Intent(this,InformacionPersonal.class);
        startActivity(perfil);
    }

    public void botonPresupuesto(View V){
        Intent presupuesto=new Intent(this, PresupuestoActivity.class);
        startActivity(presupuesto);
    }

    public void botonMenuAdmin(View V) {
        Intent i = new Intent(this, MenuAdmin.class);
        startActivity(i);
    }
}



