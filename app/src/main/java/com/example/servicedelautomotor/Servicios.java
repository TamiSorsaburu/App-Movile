package com.example.servicedelautomotor;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.room.Room;

import com.example.servicedelautomotor.coneccionBD.AppDataBase;
import com.example.servicedelautomotor.entidades.Servicio;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class Servicios extends AppCompatActivity {
    ListView li;

    private List<Servicio> servicios;
    ArrayList<Servicio> c = new ArrayList<Servicio>();

    AppDataBase appDatabase;
    ImageView imageView;

    private final static int LOCATION_REQUEST_CODE = 23;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicios);

        BottomNavigationView bottomNavigationView = findViewById(R.id.barraMenu);
        Menu menu = bottomNavigationView.getMenu();
        menu.findItem(R.id.menu_exit).setVisible(false); // Oculta el ícono de cierre de sesión

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                if (item.getItemId() == R.id.menu_home) {
                    Intent intent = new Intent(Servicios.this, Dashboard.class);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });


        li = (ListView) findViewById(R.id.lista_servicios);

        appDatabase = Room.databaseBuilder(
                getApplicationContext(),
                AppDataBase.class,
                "dbServiceAutomotor"
        ).allowMainThreadQueries().build();

        servicios = appDatabase.daoServicio().obtenerServicios();

       AdapterServicio adapterServicio=new AdapterServicio(this);
       li.setAdapter(adapterServicio);

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

                } else {

                }
                break;
        }
    }


    public void botonCancelar(View V) {
        Intent cancelar = new Intent(this, Dashboard.class);
        startActivity(cancelar);
    }

    public void botonSolicitarTurno(View V) {
        Intent solicitarT = new Intent(this, SolicitarTurno.class);
        startActivity(solicitarT);
    }




    class AdapterServicio extends ArrayAdapter<Servicio> {
        AppCompatActivity appCompatActivity;
        private Context context;

        public AdapterServicio(AppCompatActivity context){
            super(context, R.layout.item_servicio_home,servicios);
            appCompatActivity = context;
        }



        public View getView(int i, View converView, ViewGroup parent){
            LayoutInflater inflater = appCompatActivity.getLayoutInflater();
            View item = inflater.inflate(R.layout.item_servicio_home,null);

            long idServ = servicios.get(i).idServicio;
            Servicio serv = servicios.get(i);


            //vincular con los xml del item

            //@SuppressLint({"MissingInflatedId", "LocalSuppress"})
            TextView txtNombre = (TextView) item.findViewById(R.id.textServicio);

           // @SuppressLint({"MissingInflatedId", "LocalSuppress"})
            TextView txtDescripcion = (TextView) item.findViewById(R.id.textDescripcion);

            TextView txtPrecio=(TextView) item.findViewById(R.id.textPrecio);

            //@SuppressLint({"MissingInflatedId", "LocalSuppress"})
            ImageView imgServicio = item.findViewById(R.id.imageServicio);

            txtNombre.setText(servicios.get(i).nombre);
            txtDescripcion.setText(servicios.get(i).descripcion);
            txtPrecio.setText(String.valueOf(servicios.get(i).precio));
            imgServicio.setImageURI(Uri.parse(servicios.get(i).image));

            return item;
        }


        public void abrirVentanaModificacion(Servicio servicio){
            Intent intent = new Intent(this.getContext(), UpdateServiicio.class);
            intent.putExtra("class",servicio);
            startActivity(intent);
        }




    }
}