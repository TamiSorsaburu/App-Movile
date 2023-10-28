package com.example.servicedelautomotor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.servicedelautomotor.coneccionBD.AppDataBase;
import com.example.servicedelautomotor.crud.ClienteViewModel;
import com.example.servicedelautomotor.crud.ListaClientes;
import com.example.servicedelautomotor.entidades.Cliente;
import com.example.servicedelautomotor.entidades.Direccion;
import com.example.servicedelautomotor.entidades.Usuario;
import com.example.servicedelautomotor.entidades.Vehiculo;

public class CargarInformacionPersonal extends AppCompatActivity {

    EditText textTipo,textNombre,textApellido,textTelefono,textProvincia, textCalle,textAltura,textLocalidad,textMarca,textModelo,textPatente,textPostal;
    Button buttonGuardar;

    Cliente cliente;
    ImageView imagePerfil;

    AppDataBase appDatabase;

    ClienteViewModel clienteViewModel;
    Usuario usuar;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_personal);
        //setContentView(R.layout.activity_editar_info_personal);

        appDatabase = Room.databaseBuilder(
                getApplicationContext(),
                AppDataBase.class,
                "dbServiceAutomotor"
        ).allowMainThreadQueries().build();

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
        textTipo=findViewById(R.id.textTipo);


        buttonGuardar=findViewById(R.id.buttonGuardar);
        //cliente=(Cliente)getIntent().getSerializableExtra("classs");

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
       /*
       falta que acepte campos vacios
       if(direccion.getCalle().equals("")){
            direccion.setCalle("");
        }*/
        appDatabase.daoDireccion().insertarDireccion(direccion);
        Vehiculo vehiculo=new Vehiculo(textPatente.getText().toString(),textTipo.getText().toString(),textModelo.getText().toString(),textMarca.getText().toString());
        appDatabase.daoVehiculo().insertarVehiculo(vehiculo);

        usuar = (Usuario) getIntent().getSerializableExtra("class1");
        appDatabase.daoCliente().insertarCliente(new Cliente(textNombre.getText().toString(),textApellido.getText().toString(),Integer.parseInt(textTelefono.getText().toString()),
                "imagePerfil.toString()",usuar.getIdUsuario(),direccion,vehiculo));
        Toast.makeText(CargarInformacionPersonal.this,"Tus datos se guardaron Exitosamente!!!",Toast.LENGTH_LONG).show();

    }
    public void botonCancelar(View V){
        Intent cancelar=new Intent(this, Dashboard.class);
        startActivity(cancelar);
    }
    public void onClick(View v){
        registrarCliente();
        Intent intent = new Intent(this, LeerInformacionPersonal.class);
        startActivity(intent);
    }
}