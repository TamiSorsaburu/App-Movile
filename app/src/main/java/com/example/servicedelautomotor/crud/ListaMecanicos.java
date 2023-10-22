package com.example.servicedelautomotor.crud;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.servicedelautomotor.R;
import com.example.servicedelautomotor.coneccionBD.AppDataBase;
import com.example.servicedelautomotor.dao.DaoMecanico;
import com.example.servicedelautomotor.entidades.Mecanico;

import java.util.List;

public class ListaMecanicos extends AppCompatActivity {

    RecyclerView recyclerView;
    AppDataBase appDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_mecanicos);

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
