package com.example.servicedelautomotor;

import static androidx.activity.result.contract.ActivityResultContracts.*;
import static androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia.*;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
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

public class AgregarServicio extends AppCompatActivity {

    EditText campoNombre, campoDescripcion, campoPrecio;
    ImageView imgServicio;

    Button btnSeleccionarImg;

    // Registers a photo picker activity launcher in single-select mode.
    ActivityResultLauncher<PickVisualMediaRequest> pickMedia =
            registerForActivityResult(new PickVisualMedia(), uri -> {
                // Callback is invoked after the user selects a media item or closes the
                // photo picker.
                if (uri != null) {
                    Log.d("PhotoPicker", "Selected URI: " + uri);
                    imgServicio.setImageURI(uri);
                } else {
                    Log.d("PhotoPicker", "No media selected");
                }
            });

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

    public void seleccionarImagen(View view) {
        pickMedia.launch(new PickVisualMediaRequest.Builder()
                .setMediaType(PickVisualMedia.ImageOnly.INSTANCE)
                .build());

    }

    public void onClick(View view) {
        registrarServicio();
        Intent intent = new Intent(this, ListadoSucursales.class);
        startActivity(intent);
    }

    private void registrarServicio() {

        AppDataBase appDatabase = Room.databaseBuilder(
                getApplicationContext(),
                AppDataBase.class,
                "dbServiceAutomotor"
        ).allowMainThreadQueries().build();

        appDatabase.daoServicio().insertarServicio(new Servicio(campoNombre.getText().toString(),campoDescripcion.getText().toString(),Double.parseDouble(campoPrecio.getText().toString()),imgServicio.toString()));

        Toast.makeText(AgregarServicio.this, "Servicio " + campoNombre.getText().toString() + " agregado", Toast.LENGTH_SHORT).show();
    }
}