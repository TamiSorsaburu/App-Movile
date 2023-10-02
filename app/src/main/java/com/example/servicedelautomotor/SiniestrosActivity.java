package com.example.servicedelautomotor;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SiniestrosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siniestros);

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

    public void btnHome(View V) {
        Intent home = new Intent(this, Dashboard.class);
        startActivity(home);

    }

    public void btnConfirmaAyuda(View V) {
        Intent confirma = new Intent(this, activity_confirma_ayuda.class);
        startActivity(confirma);

    }
}