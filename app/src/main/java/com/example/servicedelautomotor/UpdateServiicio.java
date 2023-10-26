package com.example.servicedelautomotor;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.room.Room;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.servicedelautomotor.coneccionBD.AppDataBase;
import com.example.servicedelautomotor.entidades.Servicio;
import com.example.servicedelautomotor.entidades.Sucursal;

import org.jetbrains.annotations.NotNull;

import java.io.File;

public class UpdateServiicio extends AppCompatActivity {

    EditText campoNombre, campoDescripcion, campoPrecio;
    ImageView imgServicio;
    Button btnSeleccionarImg, btnAtras;

    AppDataBase appDatabase;

    Servicio servi;
    String realPath;
    File f;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_serviicio);

        appDatabase = Room.databaseBuilder(
                getApplicationContext(),
                AppDataBase.class,
                "dbServiceAutomotor"
        ).allowMainThreadQueries().build();

        campoNombre = findViewById(R.id.campoNombre);
        campoDescripcion = findViewById(R.id.campoDescripcion);
        campoPrecio = findViewById(R.id.campoPrecio);
        imgServicio = findViewById(R.id.imgServicio);
        btnSeleccionarImg = findViewById(R.id.btnSeleccionarImg);

        imgServicio = findViewById(R.id.imgServicio);

        servi = (Servicio) getIntent().getSerializableExtra("class");

        campoNombre.setText(servi.getNombre());
        campoDescripcion.setText(servi.getDescripcion());
        campoPrecio.setText(Double.toString(servi.getPrecio()));
        imgServicio.setImageURI(Uri.parse(servi.getImage()));
        realPath = getRealPathFromURI(Uri.parse(servi.getImage()));


    }

    public void onClick(View view) {
        editarServicio();
        Intent intent = new Intent(this, ListadoServicios.class);
        startActivity(intent);
    }

    private void editarServicio() {

        appDatabase.daoServicio().actualizarServicio(servi.idServicio,campoNombre.getText().toString(),campoDescripcion.getText().toString(), Double.parseDouble(campoPrecio.getText().toString()),realPath);
        Toast.makeText(UpdateServiicio.this, "Servicio " + campoNombre.getText().toString() + " editado", Toast.LENGTH_SHORT).show();
    }

    public void volver(View view){
        Intent intent = new Intent(this, ListadoServicios.class);
        startActivity(intent);
    }


    public void seleccionarImagen(View view) {
        cargarImagen();

    }

    private void cargarImagen(){
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
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
}