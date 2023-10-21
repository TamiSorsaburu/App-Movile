package com.example.servicedelautomotor;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.servicedelautomotor.coneccionBD.AppDataBase;
import com.example.servicedelautomotor.entidades.Direccion;
import com.example.servicedelautomotor.entidades.Mecanico;
import com.example.servicedelautomotor.entidades.Sucursal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListadoSucursales extends AppCompatActivity {


    ListView li;
    private List<Sucursal> sucursales;
    ArrayList<Sucursal> c = new ArrayList<Sucursal>();
    Sucursal sucursal;
    Direccion direccion;

    Button delete;
    Button update;
    Intent intent;

    AppDataBase appDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_sucursales);

        li = (ListView) findViewById(R.id.lista_sucursales);

        appDatabase= Room.databaseBuilder(
                getApplicationContext(),
                AppDataBase.class,
                "dbServiceAutomotor"
        ).allowMainThreadQueries().build();



        sucursales = appDatabase.daoSucursal().obtenerSucursales();

        AdapterSucursal adapterSucursal = new AdapterSucursal(this);
        li.setAdapter(adapterSucursal);
    }




    class AdapterSucursal extends ArrayAdapter<Sucursal> {
        AppCompatActivity appCompatActivity;
        private Context context;

        public AdapterSucursal(AppCompatActivity context){
            super(context, R.layout.item_sucursal,sucursales);
            appCompatActivity = context;
        }



        public View getView(int i, View converView, ViewGroup parent){
            LayoutInflater inflater = appCompatActivity.getLayoutInflater();
            View item = inflater.inflate(R.layout.item_sucursal,null);

            long idSucur = sucursales.get(i).idSucursal;
            Sucursal sucur = sucursales.get(i);
            delete = item.findViewById(R.id.borrar);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    appDatabase.daoSucursal().eliminarSucursal(idSucur);

                    Toast.makeText(getContext(), "Se eliminó la Sucursal de id " + idSucur, Toast.LENGTH_SHORT).show();

                    finish();
                    startActivity(getIntent());


                }




            });

            update= item.findViewById(R.id.editar);
            update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    abrirVentanaModificacion(sucur);
                    Toast.makeText(getContext(), "Se modificó la Sucursal de id " + idSucur, Toast.LENGTH_SHORT).show();

                }
            });







            //vincular con los xml del item
            TextView txtNombre = (TextView) item.findViewById(R.id.txtNombre);
            TextView txtLocalidad = (TextView) item.findViewById(R.id.txtLocalidad);
            txtNombre.setText(sucursales.get(i).nombre);
            txtLocalidad.setText(sucursales.get(i).direccion.localidad);
            return item;
        }

        public void abrirVentanaModificacion(Sucursal sucur){
            Intent intent = new Intent(this.getContext(), UpdateSucursal.class);
            intent.putExtra("class",sucur);
            startActivity(intent);
        }



    }


}