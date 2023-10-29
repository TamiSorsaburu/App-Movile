package com.example.servicedelautomotor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.servicedelautomotor.coneccionBD.AppDataBase;
import com.example.servicedelautomotor.entidades.Mecanico;
import com.example.servicedelautomotor.entidades.Usuario;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    FirebaseAuth mAuth;

    AppDataBase database;
    String correoFire;

    Usuario userFire;
    String correo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        database = Room.databaseBuilder(getApplicationContext(), AppDataBase.class, "dbServiceAutomotor").build();

        if (mAuth.getCurrentUser() != null) {
            //new correoFirebase().execute();
            //correo = mAuth.getCurrentUser().getEmail().toString();
            //correoFire = correo;
            //Log.d("auth3", "MainCorreo: " + correoFire);
            Intent intent = new Intent(MainActivity.this, Dashboard.class);
            //intent.putExtra("correoFire", correo);

            startActivity(intent);
            finish();
        }

        // Log.d("auth", "usuarioRegistrado: " + mAuth.getCurrentUser().getEmail());
    }

    private class correoFirebase extends AsyncTask<Void, Void, String> {


        @Override
        protected String doInBackground(Void... params) {
            //MyAccount account = new MyAccount(getApplicationContext());
            //String authtoken = account.getAuthToken());
            mAuth = FirebaseAuth.getInstance();
            String correo = mAuth.getCurrentUser().getEmail();
            //correoFire = correo;
            Log.d("auth0", "MainCorreo: " + correo);
            Intent intent = new Intent(MainActivity.this, Dashboard.class);
            intent.putExtra("correoFire", correo);

            startActivity(intent);
            //finish();
            return correo;

        }
    }



    public void buttonLogin(View view) {
        Intent intent = new Intent(this, Iniciar_sesion.class);
        startActivity(intent);
    }

    public void textRegister(View view) {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }
}