package com.example.servicedelautomotor;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
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

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import android.Manifest;

public class ListadoServicios extends AppCompatActivity {
    ListView li;
    private List<Servicio> servicios;
    ArrayList<Servicio> c = new ArrayList<Servicio>();
    Servicio servicio;
    Button delete;
    Button update;
    Intent intent;

    AppDataBase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_servicios);

        li = (ListView) findViewById(R.id.lista_servicios);

        appDatabase= Room.databaseBuilder(
                getApplicationContext(),
                AppDataBase.class,
                "dbServiceAutomotor"
        ).allowMainThreadQueries().build();



        servicios = appDatabase.daoServicio().obtenerServicios();

        AdapterServicio adapterServicio = new AdapterServicio(this);
        li.setAdapter(adapterServicio);

       // if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            //Verifica permisos para Android 6.0+
        //    checkExternalStoragePermission();
       // }

        //checkExternalStoragePermission();
        isStoragePermissionGranted();
        //checkExternalStoragePermission();


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0]== PackageManager.PERMISSION_GRANTED){


            // permission granted

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
         /*   long idSucur = sucursales.get(i).idSucursal;
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

//Editar
            update= item.findViewById(R.id.editar);
            update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    abrirVentanaModificacion(sucur);
                    Toast.makeText(getContext(), "Se modificará la Sucursal de id " + idSucur, Toast.LENGTH_SHORT).show();

                }
            });

            */




            //vincular con los xml del item
            TextView txtNombre = (TextView) item.findViewById(R.id.txtNombre);
            TextView txtDescripcion = (TextView) item.findViewById(R.id.txtDescripcion);
            ImageView imgServicio = item.findViewById(R.id.campoImagen);
            txtNombre.setText(servicios.get(i).nombre);
            txtDescripcion.setText(servicios.get(i).descripcion);

            //checkExternalStoragePermission();

            //imgServicio.setImageURI(Uri.parse(servicios.get(i).image));
            imgServicio.setImageURI(Uri.parse("/storage/emulated/0/Pictures/hombre-de-limpieza.jpg"));


            return item;
        }



     /*   public void abrirVentanaModificacion(Sucursal sucur){
            Intent intent = new Intent(this.getContext(), UpdateSucursal.class);
            intent.putExtra("class",sucur);
            startActivity(intent);
        }
        */



    }

    private void checkExternalStoragePermission() {
        int permissionCheck = ContextCompat.checkSelfPermission(
                this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            Log.i("Mensaje", "No se tiene permiso para leer.");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 225);
        } else {
            Log.i("Mensaje", "Se tiene permiso para leer!");

        }
    }

    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                Log.i("Entrada", "1");
                return true;
            } else {

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                Log.i("Entrada", "2");
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.i("Entrada", "3");
            return true;
        }
    }
}