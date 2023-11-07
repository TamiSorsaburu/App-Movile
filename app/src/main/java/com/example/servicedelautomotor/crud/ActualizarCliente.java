package com.example.servicedelautomotor.crud;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.servicedelautomotor.Contacto;
import com.example.servicedelautomotor.R;
import com.example.servicedelautomotor.coneccionBD.AppDataBase;
import com.example.servicedelautomotor.entidades.Cliente;

public class ActualizarCliente extends AppCompatActivity {
    EditText aNombre,aApellido,aTelefono,aImagen,aCalle,aAltura,aProvincia,aLocalidad,aPostal,aModelo,aMarca,aPatente;
    AppDataBase appDataBase;

    Button actualizar,cancelar;

    Cliente cliente;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_cliente);

        appDataBase = Room.databaseBuilder(
                getApplicationContext(),
                AppDataBase.class,
                "dbServiceAutomotor"
        ).allowMainThreadQueries().build();

        aNombre=findViewById(R.id.editNombre);
        aApellido=findViewById(R.id.editApellido);
        aTelefono=findViewById(R.id.editTelefono);
        aImagen=findViewById(R.id.editImagen);
        aCalle=findViewById(R.id.editCalle);
        aAltura=findViewById(R.id.editAltura);
        aProvincia=findViewById(R.id.editProvincia);
        aLocalidad=findViewById(R.id.editLocalidad);
        aPostal=findViewById(R.id.editCPostal);
        aPatente=findViewById(R.id.editPatente);
        aMarca=findViewById(R.id.editMarca);
        aModelo=findViewById(R.id.editModelo);

        actualizar=findViewById(R.id.btnActualizar);
        cancelar=findViewById(R.id.btnCancelar);

        cliente=(Cliente)getIntent().getSerializableExtra("class");

        aNombre.setText(cliente.getNombre());
        aApellido.setText(cliente.getApellido());
        aImagen.setText(cliente.getImagen());
        aTelefono.setText(Integer.toString(cliente.telefono));
        aCalle.setText(cliente.direccion.calle);
        aAltura.setText(cliente.direccion.altura);
        aLocalidad.setText(cliente.direccion.localidad);
        aProvincia.setText(cliente.direccion.provincia);
        aPostal.setText(Integer.toString(cliente.direccion.codigoPostal));
        aMarca.setText(cliente.Vehiculo.marca);
        aModelo.setText(cliente.Vehiculo.modelo);
        aPatente.setText(cliente.Vehiculo.patente);

    }

    public void onClick(View v){
        //editarCliente();
        Intent intent = new Intent(this, ListaClientes.class);
        startActivity(intent);
    }
    

 /*   private void editarCliente(){
        appDataBase.daoCliente().
                actualizarCliente(cliente.idCliente,
                        aNombre.getText().toString(),
                        aApellido.getText().toString(),
                        aImagen.getText().toString(),
                        Integer.parseInt(aTelefono.getText().toString()),
                        aCalle.getText().toString(),
                        aAltura.getText().toString(),
                        aLocalidad.getText().toString(),
                        aProvincia.getText().toString(),
                        Integer.parseInt(aPostal.getText().toString()),
                        aModelo.getText().toString(),
                        aMarca.getText().toString(),
                        aPatente.getText().toString());

    }

  */

    public void botonCancelar(View V){
        Intent intent=new Intent(this, ListaClientes.class);
        startActivity(intent);
    }
}
