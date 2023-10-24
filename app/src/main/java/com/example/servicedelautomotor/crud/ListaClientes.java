package com.example.servicedelautomotor.crud;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.servicedelautomotor.R;
import com.example.servicedelautomotor.coneccionBD.AppDataBase;
import com.example.servicedelautomotor.entidades.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ListaClientes extends AppCompatActivity {
    ListView lista;
    private List<Cliente> clientes;
    ArrayList<Cliente> c = new ArrayList<Cliente>();
    ImageButton eliminar, editar;
    AppDataBase appDatabase;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_clientes);

        lista = (ListView) findViewById(R.id.listaClientes);

        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDataBase.class, "dbServiceAutomotor").allowMainThreadQueries().build();

        clientes = appDatabase.daoCliente().obtenerClientes();

        AdapterCliente adapterCliente = new AdapterCliente(this);
        lista.setAdapter(adapterCliente);

    }

    class AdapterCliente extends ArrayAdapter<Cliente> {
        AppCompatActivity appCompatActivity;
        private Context context;

        public AdapterCliente(AppCompatActivity context) {
            super(context, R.layout.item_cliente, clientes);
            appCompatActivity = context;
        }


        public View getView(int i, View converView, ViewGroup parent) {
            LayoutInflater inflater = appCompatActivity.getLayoutInflater();
            View item = inflater.inflate(R.layout.item_cliente, null);

            long idClient = clientes.get(i).idCliente;
            Cliente client = clientes.get(i);
            eliminar = item.findViewById(R.id.btnEliminar);
            eliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    appDatabase.daoCliente().eliminarCliente(idClient);
                    startActivity(getIntent());
                    //Toast.makeText(getContex, "Se eliminó la Sucursal de id " + idSucur, Toast.LENGTH_SHORT).show();
                    finish();


                }
            });

            editar = item.findViewById(R.id.btnEditar);
            editar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    abrirVentanaModificacion(client);
                    //Toast.makeText(getContext(), "Se modificará la Sucursal de id " + idSucur, Toast.LENGTH_SHORT).show();

                }
            });

            TextView txtNombre = (TextView) item.findViewById(R.id.txtNombre);
            TextView txtApellido = (TextView) item.findViewById(R.id.txtApellido);
            txtNombre.setText(clientes.get(i).nombre);
            txtApellido.setText(clientes.get(i).apellido);
            //txtLocalidad.setText(clientes.get(i).direccion.localidad);
            return item;
        }

        public void abrirVentanaModificacion(Cliente client) {
            Intent intent = new Intent(this.getContext(), ActualizarCliente.class);
            intent.putExtra("class", client);
            startActivity(intent);

        }
    }
}
