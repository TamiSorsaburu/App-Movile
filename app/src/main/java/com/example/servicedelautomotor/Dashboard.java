package com.example.servicedelautomotor;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.room.Room;

import com.example.servicedelautomotor.coneccionBD.AppDataBase;
import com.example.servicedelautomotor.dao.DaoCliente;
import com.example.servicedelautomotor.entidades.Cliente;
import com.example.servicedelautomotor.entidades.Usuario;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class Dashboard extends AppCompatActivity {
    FirebaseAuth mAuth;
    private ImageView adminImage,imgMiPerfil;
    private TextView adminText;
    AppDataBase database;

    private boolean datosCargados = false;

    Usuario usua;
    private final static int LOCATION_REQUEST_CODE = 23;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //setContentView(R.layout.activity_bienvenida);

        //String name = getIntent().getStringExtra("nombreUsuario");
        mAuth = FirebaseAuth.getInstance();
        //String correoFirebase = getIntent().getStringExtra("correoFire");
        //Log.d("dash", "DashbCorreo: " + correoFirebase);

        database = Room.databaseBuilder(getApplicationContext(), AppDataBase.class, "dbServiceAutomotor").build();
        //usua = database.daoUsuario().getUsuarioPorCorreo(correoFirebase);
       // usua = database.daoUsuario().getUsuarioById(1);
       // if(usua != null){
       //     String name = usua.getNombreUsuario();
       //     TextView nameTextView = findViewById(R.id.name);
      //      nameTextView.setText(name);
     //   }else{
      //      Toast.makeText(this, "Ningun usuario logueado", Toast.LENGTH_SHORT).show();
      //  }
//

        findViewById(R.id.imgMiPerfil).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDialogo();
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.barraMenu);



        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                if(item.getItemId() == R.id.menu_exit) {
                    mAuth.signOut();
                    Intent intent = new Intent(Dashboard.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    return true;
                }
                return false;
            }
        });

        String currentUserEmail = mAuth.getCurrentUser().getEmail();

        // Encuentra la imagen Admin por su ID
        adminImage = findViewById(R.id.imgAdmin);
        adminText = findViewById(R.id.textAdmin);

        if(currentUserEmail != null && currentUserEmail.equals("admin@gmail.com")){
            // Si el usuario es un administrador, muestra el botón Admin
            adminImage.setVisibility(View.VISIBLE);
            adminText.setVisibility(View.VISIBLE);
        }else {
            // Si el usuario no es un administrador, oculta el botón Admin
            adminImage.setVisibility(View.GONE);
            adminText.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) !=
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
    private void mostrarDialogo(){
        AlertDialog.Builder builder=new AlertDialog.Builder(Dashboard.this);
        builder.setTitle("Cargar perfil");
        builder.setMessage("Completa tu perfil y disfruta de todos los servicios para tu vehiculo!")
                .setPositiveButton("si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"Perfil",Toast.LENGTH_SHORT).show();
                        Intent perfil=new Intent(Dashboard.this,LeerInformacionPersonal.class);
                        startActivity(perfil);
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent perfil=new Intent(Dashboard.this,Dashboard.class);
                        startActivity(perfil);
                        Toast.makeText(getApplicationContext(),"Cancelar..",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }).show();

    }
    //para boton nosotros
    public void botonNosotros(View V) {
        Intent i = new Intent(this, QuienesSomos.class);
        startActivity(i);
    }

    //metodo para botones
    public void botonMecanico(View V) {
        Intent mecanico = new Intent(this, MecanicoActivity.class);
        startActivity(mecanico);
    }

    public void botonSiniestro(View V) {
        Intent siniestro = new Intent(this, SelectSiniestro.class);
        startActivity(siniestro);
    }

    public void botonGrua(View V) {
        Intent grua = new Intent(this, SiniestrosActivity.class);
        startActivity(grua);
    }

    public void botonMisTurnos(View v) {
        Intent miTurno = new Intent(this, MisTurnos.class);
        startActivity(miTurno);
    }

    public void botonServicio(View V) {
        Intent servicio = new Intent(this, Servicios.class);
        startActivity(servicio);

    }

    public void botonMiAuto(View V) {
        Intent auto = new Intent(this, InformacionAuto.class);
        startActivity(auto);
    }

    public void botonContacto(View V){
        Intent contacto=new Intent(this, Contacto.class);
        startActivity(contacto);
    }

    public void botonPerfil(View view){
        /*
        se necesita saber que usuario esta logeado para traer su info de la base de datos
        }  */
        Intent perfil=new Intent(this, LeerInformacionPersonal.class);
        startActivity(perfil);


    }


    public void botonPresupuesto(View V){
        Intent presupuesto=new Intent(this, PresupuestoActivity.class);
        startActivity(presupuesto);
    }

    public void botonMenuAdmin(View V) {
        Intent i = new Intent(this, MenuAdmin.class);
        startActivity(i);
    }
}



