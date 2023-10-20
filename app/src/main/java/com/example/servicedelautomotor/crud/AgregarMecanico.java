package com.example.servicedelautomotor.crud;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.servicedelautomotor.R;
import com.example.servicedelautomotor.coneccionBD.AppDataBase;
import com.example.servicedelautomotor.entidades.Mecanico;

public class AgregarMecanico extends AppCompatActivity {
    Button guardar,lista;
    EditText nombreSave,apellidoSave,telefonoSave,saveSucursal;

    Switch switchG;
    AppDataBase appDataBase;
    TextView guardia;

    @SuppressLint("MissingInflatedId")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_mecanico);

            guardar = (Button) findViewById(R.id.botonGuardar);
            lista=(Button) findViewById(R.id.btnIrLista);

            nombreSave = (EditText) findViewById(R.id.textNombre);
            apellidoSave = (EditText) findViewById(R.id.txtApellido);
            telefonoSave = (EditText) findViewById(R.id.txtTelefono);
            saveSucursal=(EditText) findViewById(R.id.txtSucursal);

            switchG = (Switch) findViewById(R.id.switchGuardia);
            guardia = (TextView) findViewById(R.id.txtGuardia);

            appDataBase = AppDataBase.getInstance(this);
            //appDataBase = Room.databaseBuilder(getApplicationContext(), AppDataBase.class, "dbServiceAutomotor").build();


        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appDataBase.daoMecanico().insertarMecanico(new Mecanico(nombreSave.getText().toString()
                ,apellidoSave.getText().toString()
                ,Integer.parseInt(telefonoSave.getText().toString()
                ), guardia.getText().toString(), Integer.parseInt(saveSucursal.getText().toString())));
                Toast.makeText(AgregarMecanico.this,"Mecanico cargado exitosamente!",Toast.LENGTH_LONG).show();
            }
        });

        lista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ListaMecanicos.class));
            }
        });

        }

    public void onclick(View view){
        if(view.getId()==R.id.switchGuardia){
            if (switchG.isChecked()) {
                guardia.setText("Disponible");
            }else{
                guardia.setText("No disponible");
            }
        }
    }


}

