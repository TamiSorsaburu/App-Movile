package com.example.servicedelautomotor;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.servicedelautomotor.coneccionBD.AppDataBase;
import com.example.servicedelautomotor.entidades.Direccion;
import com.example.servicedelautomotor.entidades.Servicio;
import com.example.servicedelautomotor.entidades.Sucursal;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import android.Manifest;

import org.jetbrains.annotations.NotNull;

public class ListadoServicios extends AppCompatActivity {
    ListView li;
    private List<Servicio> servicios;
    ArrayList<Servicio> c = new ArrayList<Servicio>();
    Servicio servicio;
    Button delete;
    Button update;
    Intent intent;

    AppDataBase appDatabase;
    private final static int LOCATION_REQUEST_CODE = 23;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_servicios);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //toolbar.setTitle(username);
        setSupportActionBar(toolbar);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        } else {
            throw new NullPointerException("Something went wrong");
        }

        BottomNavigationView bottomNavigationView = findViewById(R.id.barraMenu);
        Menu menu = bottomNavigationView.getMenu();
        menu.findItem(R.id.menu_exit).setVisible(false); // Oculta el ícono de cierre de sesión

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                if (item.getItemId() == R.id.menu_home) {
                    Intent intent = new Intent(ListadoServicios.this, Dashboard.class);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });

        li = (ListView) findViewById(R.id.lista_servicios);

        appDatabase= Room.databaseBuilder(
                getApplicationContext(),
                AppDataBase.class,
                "dbServiceAutomotor"
        ).allowMainThreadQueries().build();



        servicios = appDatabase.daoServicio().obtenerServicios();

        AdapterServicio adapterServicio = new AdapterServicio(this);
        li.setAdapter(adapterServicio);


    }

    @Override
    protected void onStart() {
        super.onStart();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    LOCATION_REQUEST_CODE);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case LOCATION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    /*Toast.makeText(this, "Gracias por conceder los permisos para " +
                            "leer el almacenamiento, estos permisos son necesarios para poder " +
                            "escoger tu foto de perfil", Toast.LENGTH_SHORT).show();*/
                } else {
                   // Toast.makeText(this, "No podemos realizar el registro si no nos concedes los permisos para leer el almacenamiento.", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }


    class AdapterServicio extends ArrayAdapter<Servicio> {
        AppCompatActivity appCompatActivity;
        private Context context;

        public AdapterServicio(AppCompatActivity context){
            super(context, R.layout.item_servicio,servicios);
            appCompatActivity = context;
        }



        public View getView(int i, View converView, ViewGroup parent){
            LayoutInflater inflater = appCompatActivity.getLayoutInflater();
            View item = inflater.inflate(R.layout.item_servicio,null);
//Borrar
            long idServ = servicios.get(i).idServicio;
            Servicio serv = servicios.get(i);
            delete = item.findViewById(R.id.borrar);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    appDatabase.daoServicio().eliminarServicio(idServ);

                    Toast.makeText(getContext(), "Se eliminó el Servicio" + servicios.get(i).nombre, Toast.LENGTH_SHORT).show();

                    finish();
                    startActivity(getIntent());
                }
            });

//Editar

            update= item.findViewById(R.id.editar);
            update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    abrirVentanaModificacion(serv);
                    Toast.makeText(getContext(), "Se modificará el servicio " + servicios.get(i).nombre, Toast.LENGTH_SHORT).show();

                }
            });

            //vincular con los xml del item
            TextView txtNombre = (TextView) item.findViewById(R.id.txtNombre);
            TextView txtDescripcion = (TextView) item.findViewById(R.id.txtDescripcion);
            ImageView imgServicio = item.findViewById(R.id.campoImagen);
            txtNombre.setText(servicios.get(i).nombre);
            txtDescripcion.setText(servicios.get(i).descripcion);

            imgServicio.setImageURI(Uri.parse(servicios.get(i).image));

            return item;
        }



      public void abrirVentanaModificacion(Servicio servicio){
            Intent intent = new Intent(this.getContext(), UpdateServiicio.class);
            intent.putExtra("class",servicio);
            startActivity(intent);
        }


      public void volverAtras(View view){
          Intent intent = new Intent(this.getContext(), AgregarServicio.class);
          startActivity(intent);
      }




    }

}