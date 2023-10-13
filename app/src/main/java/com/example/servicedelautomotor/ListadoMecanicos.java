package com.example.servicedelautomotor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import com.example.servicedelautomotor.coneccionBD.AppDataBase;
import com.example.servicedelautomotor.entidades.Mecanico;

import java.util.List;

public class ListadoMecanicos extends AppCompatActivity {
    TextView tvMecanicos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_mecanicos);

        tvMecanicos = findViewById(R.id.tvMecanicos);

        AppDataBase appDatabase = Room.databaseBuilder(
                getApplicationContext(),
                AppDataBase.class,
                "dbServiceAutomotor"
        ).allowMainThreadQueries().build();

        List<Mecanico> mecanicos = appDatabase.daoMecanico().obtenerMecanicos();
        System.out.println("lista de mecanicos");
        String texto = "";
        for (int i=0;i<mecanicos.size();i++) {
            texto = texto + "Mecanico: Id:" + mecanicos.get(i).idMecanico + "=" + mecanicos.get(i).nombre + "," + mecanicos.get(i).apellido + "," + mecanicos.get(i).telefono + "\n";

        }
        tvMecanicos.setText(texto);
    }


}