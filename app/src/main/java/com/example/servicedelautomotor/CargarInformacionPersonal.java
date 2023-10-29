package com.example.servicedelautomotor;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.servicedelautomotor.coneccionBD.AppDataBase;
import com.example.servicedelautomotor.crud.ClienteViewModel;
import com.example.servicedelautomotor.crud.ListaClientes;
import com.example.servicedelautomotor.entidades.Cliente;
import com.example.servicedelautomotor.entidades.Direccion;
import com.example.servicedelautomotor.entidades.Usuario;
import com.example.servicedelautomotor.entidades.Vehiculo;

import java.io.File;

public class CargarInformacionPersonal extends AppCompatActivity {

    EditText textTipo,textNombre,textApellido,textTelefono,textProvincia, textCalle,textAltura,textLocalidad,textMarca,textModelo,textPatente,textPostal;
    Button buttonGuardar;

    Cliente cliente;
    ImageView imagePerfil;
    File f;
    String realPath;
    AppDataBase appDatabase;

    ClienteViewModel clienteViewModel;
    Usuario usuar;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_personal);
        //setContentView(R.layout.activity_editar_info_personal);

        appDatabase = Room.databaseBuilder(
                getApplicationContext(),
                AppDataBase.class,
                "dbServiceAutomotor"
        ).allowMainThreadQueries().build();

        textNombre=findViewById(R.id.textNombre);
        textApellido=findViewById(R.id.textApellido);
        textTelefono=findViewById(R.id.textTelefono);
        imagePerfil=findViewById(R.id.imagePerfil);
        textProvincia=findViewById(R.id.textProvincia);
        textCalle=findViewById(R.id.textCalle);
        textAltura=findViewById(R.id.textAltura);
        textLocalidad=findViewById(R.id.textLocalidad);
        textMarca=findViewById(R.id.textMarca);
        textModelo=findViewById(R.id.textModelo);
        textPatente=findViewById(R.id.textPatente);
        textPostal=findViewById(R.id.textPostal);
        textTipo=findViewById(R.id.textTipo);


        buttonGuardar=findViewById(R.id.buttonGuardar);
        //cliente=(Cliente)getIntent().getSerializableExtra("classs");

    }


    public void btnListarClientes(View view) {
        Intent intent = new Intent(this, ListaClientes.class);
        startActivity(intent);
    }

    public void cargarImg(View view){
        cargarImagen();
    }

    private void cargarImagen(){
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        i.setType("image/");
        startActivityForResult(Intent.createChooser(i,"seleccione la imagen"),10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Uri uri = data.getData();
            final String realPath = getRealPathFromURI(uri);
            this.f = new File(realPath);
            this.imagePerfil.setImageURI(uri);
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
        //this.imagen = result;
        return result;
    }

    private void registrarCliente(){
        AppDataBase appDatabase = Room.databaseBuilder(
                getApplicationContext(),
                AppDataBase.class,
                "dbServiceAutomotor"
        ).allowMainThreadQueries().build();

        //usuar = (Usuario) getIntent().getSerializableExtra("class2");
       // if(usuar == null){
       //     Toast.makeText(this, "Ningún usuario logueado, no se puede cargar informacion", Toast.LENGTH_SHORT).show();
       // }else{
            Direccion direccion=new Direccion(textCalle.getText().toString(),textAltura.getText().toString(),textLocalidad.getText().toString(),textProvincia.getText().toString(),Integer.parseInt(textPostal.getText().toString()));
       /*
       falta que acepte campos vacios
       if(direccion.getCalle().equals("")){
            direccion.setCalle("");
        }*/
            appDatabase.daoDireccion().insertarDireccion(direccion);
            Vehiculo vehiculo=new Vehiculo(textPatente.getText().toString(),textTipo.getText().toString(),textModelo.getText().toString(),textMarca.getText().toString());
            appDatabase.daoVehiculo().insertarVehiculo(vehiculo);



            appDatabase.daoCliente().insertarCliente(new Cliente(textNombre.getText().toString(),textApellido.getText().toString(),Integer.parseInt(textTelefono.getText().toString()),
                    realPath,1,direccion,vehiculo));
            Toast.makeText(CargarInformacionPersonal.this,"Tus datos se guardaron Exitosamente!!!",Toast.LENGTH_LONG).show();
        }
       // Log.d("real", "realPath: " + realPath);



   // }
    public void botonCancelar(View V){
        Intent cancelar=new Intent(this, Dashboard.class);
        startActivity(cancelar);
    }
    public void onClick(View v){
        registrarCliente();
        Intent intent = new Intent(this, Dashboard.class);
        startActivity(intent);
    }
}