package com.example.servicedelautomotor;

import static androidx.activity.result.contract.ActivityResultContracts.*;
import static androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia.*;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.room.Room;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Path;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.servicedelautomotor.coneccionBD.AppDataBase;
import com.example.servicedelautomotor.entidades.Direccion;
import com.example.servicedelautomotor.entidades.Servicio;
import com.example.servicedelautomotor.entidades.Sucursal;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AgregarServicio extends AppCompatActivity {

    EditText campoNombre, campoDescripcion, campoPrecio;
    ImageView imgServicio;

    Button btnSeleccionarImg;
    String uriString;
    String realPath;
    Uri uriAConvertir;
    File auxFile;
    File f;



    private final static int LOCATION_REQUEST_CODE = 23;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_servicio);
        campoNombre = findViewById(R.id.campoNombre);
        campoDescripcion = findViewById(R.id.campoDescripcion);
        campoPrecio = findViewById(R.id.campoPrecio);
        btnSeleccionarImg = findViewById(R.id.btnSeleccionarImg);

        imgServicio = findViewById(R.id.imgServicio);



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
                    Toast.makeText(this, "Gracias por conceder los permisos para " +
                            "leer el almacenamiento, estos permisos son necesarios para poder " +
                            "escoger tu foto de perfil", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "No podemos realizar el registro si no nos concedes los permisos para leer el almacenamiento.", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public void seleccionarImagen(View view) {
        cargarImagen();

    }

    public void onClick(View view) {
        registrarServicio();
        Intent intent = new Intent(this, ListadoServicios.class);
        startActivity(intent);
    }

    private void registrarServicio() {

        AppDataBase appDatabase = Room.databaseBuilder(
                getApplicationContext(),
                AppDataBase.class,
                "dbServiceAutomotor"
        ).allowMainThreadQueries().build();

        appDatabase.daoServicio().insertarServicio(new Servicio(campoNombre.getText().toString(),campoDescripcion.getText().toString(),Double.parseDouble(campoPrecio.getText().toString()),realPath));

        Toast.makeText(AgregarServicio.this, "Servicio " + campoNombre.getText().toString() + " agregado", Toast.LENGTH_SHORT).show();
    }

    private void cargarImagen(){
        Intent i = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        i.setType("image/");
        startActivityForResult(Intent.createChooser(i,"seleccione la aplicación"),10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Uri uri = data.getData();
            final String realPath = getRealPathFromURI(uri);
            this.f = new File(realPath);
            this.imgServicio.setImageURI(uri);
        }
    }

    private String getRealPathFromURI(Uri contentUri) {
        String result;
        Cursor cursor = getContentResolver().query(contentUri, null, null, null, null);
        if (cursor == null) {
            result = contentUri.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        Log.d("result", "result: " + result);
        this.realPath = result;
        return result;
    }

    public void btnListar(View view) {
        Intent intent = new Intent(this, ListadoServicios.class);
        startActivity(intent);
    }




}