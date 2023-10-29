package com.example.servicedelautomotor.crud;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.servicedelautomotor.Dashboard;
import com.example.servicedelautomotor.MisTurnos;
import com.example.servicedelautomotor.R;
import com.example.servicedelautomotor.coneccionBD.AppDataBase;
import com.example.servicedelautomotor.dao.DaoMecanico;
import com.example.servicedelautomotor.entidades.Mecanico;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class ListaMecanicos extends AppCompatActivity {

    RecyclerView recyclerView;
    AppDataBase appDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_mecanicos);

        BottomNavigationView bottomNavigationView = findViewById(R.id.barraMenu);
        Menu menu = bottomNavigationView.getMenu();
        menu.findItem(R.id.menu_exit).setVisible(false); // Oculta el ícono de cierre de sesión

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                if (item.getItemId() == R.id.menu_home) {
                    Intent intent = new Intent(ListaMecanicos.this, Dashboard.class);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });

        appDataBase = AppDataBase.getInstance(this);
        listarMecanicos();
    }

    public void listarMecanicos(){
        DaoMecanico daoMecanico=appDataBase.daoMecanico();

        recyclerView=(RecyclerView) findViewById(R.id.recyclerLista);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Mecanico>mecanicos=daoMecanico.obtenerMecanicos();
        AdaptadorMecanico adaptadorMecanico= new AdaptadorMecanico(mecanicos);
        recyclerView.setAdapter(adaptadorMecanico);


    }
}
