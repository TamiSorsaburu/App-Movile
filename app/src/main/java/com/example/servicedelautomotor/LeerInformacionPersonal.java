package com.example.servicedelautomotor;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import com.example.servicedelautomotor.coneccionBD.AppDataBase;
import com.example.servicedelautomotor.crud.ClienteViewModel;
import com.example.servicedelautomotor.entidades.Cliente;

public class LeerInformacionPersonal extends AppCompatActivity {
    Cliente cliente;
    ImageView imagePerfil;

    AppDataBase appDatabase;

    ClienteViewModel clienteViewModel;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_info_personal);

        cliente=(Cliente)getIntent().getSerializableExtra("classs");

        appDatabase = Room.databaseBuilder(
                getApplicationContext(),
                AppDataBase.class,
                "dbServiceAutomotor"
        ).allowMainThreadQueries().build();

        //para ver los datos del cliente registrado
        clienteViewModel=new ViewModelProvider(this).get(ClienteViewModel.class);
        clienteViewModel.getClienteLiveData().observe(this, new Observer<Cliente>() {
            @Override
            public void onChanged(Cliente cliente) {
                if(cliente != null){
                    TextView pNombre=findViewById(R.id.pNombre);
                    pNombre.setText(cliente.getNombre());

                    TextView pApellido=findViewById(R.id.pApellido);
                    pApellido.setText(cliente.getApellido());

                    TextView pTelefono=findViewById(R.id.pTelefono);
                    pTelefono.setText(String.valueOf(cliente.getTelefono()));

                    TextView pCalle=findViewById(R.id.pCalle);
                    pCalle.setText(cliente.direccion.calle);

                    TextView pAltura=findViewById(R.id.pAltura);
                    pAltura.setText(cliente.direccion.altura);

                    TextView pLocalidad=findViewById(R.id.pLocalidad);
                    pLocalidad.setText(cliente.direccion.localidad);

                    TextView pProvincia=findViewById(R.id.pProvincia);
                    pProvincia.setText(cliente.direccion.provincia);

                    TextView pPostal=findViewById(R.id.pPostal);
                    pPostal.setText(String.valueOf(cliente.direccion.codigoPostal));

                    TextView pMarca=findViewById(R.id.pMarca);
                    pMarca.setText(cliente.getVehiculo().marca);

                    TextView pModelo=findViewById(R.id.pModelo);
                    pModelo.setText(cliente.getVehiculo().modelo);

                    TextView pPatente=findViewById(R.id.pPatente);
                    pPatente.setText(cliente.getVehiculo().patente);

                    TextView pTipo=findViewById(R.id.pTipo);
                    pTipo.setText(cliente.getVehiculo().tipoVehiculo);

                }
            }
        });


    }
}
