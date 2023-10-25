package com.example.servicedelautomotor;

import static androidx.activity.result.contract.ActivityResultContracts.*;
import static androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia.*;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia;
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

    // Registers a photo picker activity launcher in single-select mode.
    ActivityResultLauncher<PickVisualMediaRequest> pickMedia =
            registerForActivityResult(new PickVisualMedia(), uri -> {
                // Callback is invoked after the user selects a media item or closes the
                // photo picker.
                if (uri != null) {
                    Log.d("PhotoPicker", "Selected URI: " + uri);
                    imgServicio.setImageURI(uri);
                    uriString = String.valueOf(uri);
                    Log.d("UriGuardada", "URI guardada: " + uriString);
                    String path = uri.getPath();
                    Log.d("Path", "Path: " + path);

                    uriAConvertir = uri;






                } else {
                    Log.d("PhotoPicker", "No media selected");
                }
            });

    public AgregarServicio() throws IOException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_servicio);
        campoNombre = findViewById(R.id.campoNombre);
        campoDescripcion = findViewById(R.id.campoDescripcion);
        campoPrecio = findViewById(R.id.campoPrecio);
        btnSeleccionarImg = findViewById(R.id.btnSeleccionarImg);

        imgServicio = findViewById(R.id.imgServicio);

        //isStoragePermissionGranted();
        checkExternalStoragePermission();

   /*     File imagenArchivo = null;
        try {
            imagenArchivo = crearImagen();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Uri fotoUri = FileProvider.getUriForFile(this, "com.example.servicedelautomotor.files", imagenArchivo);
        Log.d("fotoUri", "fotoUri: " + fotoUri);

    */



    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0]== PackageManager.PERMISSION_GRANTED){


            // permission granted

        }
    }
 /*

 private File crearImagen() throws IOException {

        String nombreImagen = "foto_";
        //File directorio = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File file = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        //File file = new File(Environment.getExternalStorageDirectory(), "read.me");
        Uri uris = Uri.fromFile(file);
        auxFile = new File(uriAConvertir.getPath());
        //assertEquals(file.getAbsolutePath(), auxFile.getAbsolutePath());
        //Log.d("Path2", "PathStorege: " + auxFile);
        //Log.d("Path3", "PathUris: " + uris);

        //File imagen = File.createTempFile(nombreImagen, ".jpg", directorio);
        //File imagen = File.createTempFile(nombreImagen, ".jpg", file);
        //String rutaImagen = imagen.getAbsolutePath();
        //Log.d("ruteImagen", "rutaImagen: " + rutaImagen);
        //return imagen;



    }
    private Context context;
    File file = new File(getRealPathFromURI(context,uriAConvertir));


    public String getPath(Uri uri)
    {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = getContentResolver().query(uri, projection, null, null,null);
        //if (cursor == null) return null;
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String s=cursor.getString(column_index);
        cursor.close();
        Log.d("nuevaPrueba", "nuevaPrueba: " + s);
        return s;
    }

    public String getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Images.Media.DATA };
            cursor = context.getContentResolver().query(contentUri,  proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            Log.d("nuevaPrueba", "nuevaPrueba: " + cursor.getString(column_index));
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

  */

    public void seleccionarImagen(View view) {
        cargarImagen();
       // pickMedia.launch(new PickVisualMediaRequest.Builder()
       //         .setMediaType(PickVisualMedia.ImageOnly.INSTANCE)
       //         .build());

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
}