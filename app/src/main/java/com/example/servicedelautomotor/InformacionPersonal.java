package com.example.servicedelautomotor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.servicedelautomotor.coneccionBD.AppDataBase;
import com.example.servicedelautomotor.crud.ListaClientes;
import com.example.servicedelautomotor.entidades.Cliente;
import com.example.servicedelautomotor.entidades.Direccion;
import com.example.servicedelautomotor.entidades.Vehiculo;

public class InformacionPersonal extends AppCompatActivity {

    EditText textNombre,textApellido,textTelefono,textProvincia, textCalle,textAltura,textLocalidad,textMarca,textModelo,textPatente,textPostal;
    Button buttonGuardar;

    ImageView imagePerfil;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_personal);

        textNombre=findViewById(R.id.textNombre);
        textApellido=findViewById(R.id.textApellido);
        textTelefono=findViewById(R.id.textTelefono);
        imagePerfil=findViewById(R.id.imagePerfil);
        textProvincia=findViewById(R.id.textProvincia);
        textCalle=findViewById(R.id.textCalle);
        textAltura=findViewById(R.id.textAltura);
        textLocalidad=findViewById(R.id.textLocalidad);
        textMarca=findViewById(R.id.textMarca);
        textModelo=findViewById(R.id.textModelo);
        textPatente=findViewById(R.id.textPatente);
        textPostal=findViewById(R.id.textPostal);


        buttonGuardar=findViewById(R.id.buttonGuardar);
    }

    public void onClick(View v){
        registrarCliente();
        Intent intent = new Intent(this, InformacionPersonal.class);
        startActivity(intent);
    }
    public void btnListarClientes(View view) {
        Intent intent = new Intent(this, ListaClientes.class);
        startActivity(intent);
    }

    private void registrarCliente(){
        AppDataBase appDatabase = Room.databaseBuilder(
                getApplicationContext(),
                AppDataBase.class,
                "dbServiceAutomotor"
        ).allowMainThreadQueries().build();

        Direccion direccion=new Direccion(textCalle.getText().toString(),textAltura.getText().toString(),textLocalidad.getText().toString(),textProvincia.getText().toString(),Integer.parseInt(textPostal.getText().toString()));
        Vehiculo vehiculo=new Vehiculo(textPatente.getText().toString(),textModelo.getText().toString(),textMarca.getText().toString());
        appDatabase.daoCliente().insertarCliente(new Cliente(textNombre.getText().toString(),textApellido.getText().toString(),Integer.parseInt(textTelefono.getText().toString()),
                imagePerfil.toString().toString(),direccion,vehiculo));
        Toast.makeText(InformacionPersonal.this,"Tus datos se guardaron Exitosamente!!!",Toast.LENGTH_LONG).show();
    }
    public void botonCancelar(View V){
        Intent cancelar=new Intent(this, Dashboard.class);
        startActivity(cancelar);
    }
}