package com.example.servicedelautomotor.crud;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.servicedelautomotor.Dashboard;
import com.example.servicedelautomotor.ListadoServicios;
import com.example.servicedelautomotor.R;
import com.example.servicedelautomotor.coneccionBD.AppDataBase;
import com.example.servicedelautomotor.entidades.Cliente;
import com.example.servicedelautomotor.entidades.Direccion;
import com.example.servicedelautomotor.entidades.Vehiculo;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AgregarCliente extends AppCompatActivity {

    EditText textTipo,textNombre,textApellido,textTelefono,textProvincia, textCalle,textAltura,textLocalidad,textMarca,textModelo,textPatente,textPostal;
    Button buttonGuardar;
    ImageView imagePerfil;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_cliente);

        BottomNavigationView bottomNavigationView = findViewById(R.id.barraMenu);
        Menu menu = bottomNavigationView.getMenu();
        menu.findItem(R.id.menu_exit).setVisible(false); // Oculta el ícono de cierre de sesión

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                if (item.getItemId() == R.id.menu_home) {
                    Intent intent = new Intent(AgregarCliente.this, Dashboard.class);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });

        textNombre=findViewById(R.id.textNombre);
        textApellido=findViewById(R.id.textApellido);
        textTelefono=findViewById(R.id.textTelefono);
        imagePerfil=findViewById(R.id.imagePerfil);
        textCalle=findViewById(R.id.textCalle);
        textAltura=findViewById(R.id.textAltura);
        textLocalidad=findViewById(R.id.textLocalidad);
        textMarca=findViewById(R.id.textMarca);
        textModelo=findViewById(R.id.textModelo);
        textPatente=findViewById(R.id.textPatente);
        textPostal=findViewById(R.id.textPostal);
        textProvincia=findViewById(R.id.textProvincia);
        textTipo=findViewById(R.id.textTipo);

        buttonGuardar=findViewById(R.id.btnRegistro);
}
    public void onClick(View v){
        registrarCliente();
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
        Vehiculo vehiculo=new Vehiculo(textPatente.getText().toString(),textTipo.getText().toString(),textModelo.getText().toString(),textMarca.getText().toString());
       // appDatabase.daoCliente().insertarCliente(new Cliente(textNombre.getText().toString(),textApellido.getText().toString(),Integer.parseInt(textTelefono.getText().toString()),imagePerfil.toString(),direccion,vehiculo));
        Toast.makeText(AgregarCliente.this,"Tus datos se guardaron Exitosamente!!!",Toast.LENGTH_LONG).show();
    }



    public void botonCancelar(View V){
        Intent cancelar=new Intent(this, Dashboard.class);
        startActivity(cancelar);
    }
    public void btnListarClientes(View view) {
        Intent intent = new Intent(this, ListaClientes.class);
        startActivity(intent);
    }
}
