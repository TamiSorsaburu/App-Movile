package com.example.servicedelautomotor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SiniestrosActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener {
    TextView txtLatitud, txtLongitd;
    GoogleMap mMap;

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

        txtLatitud = findViewById(R.id.TextUbicacion);
        txtLongitd = findViewById(R.id.TextUbicacion);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        BottomNavigationView bottomNavigationView = findViewById(R.id.barraMenu);
        Menu menu = bottomNavigationView.getMenu();
        menu.findItem(R.id.menu_exit).setVisible(false); // Oculta el ícono de cierre de sesión

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                if (item.getItemId() == R.id.menu_home) {
                    Intent intent = new Intent(SiniestrosActivity.this, Dashboard.class);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });
    }

    public void btnHome(View V) {
        Intent home = new Intent(this, Dashboard.class);
        startActivity(home);

    }

    public void btnConfirmaAyuda(View V) {
        Intent confirma = new Intent(this, activity_confirma_ayuda.class);
        startActivity(confirma);

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        this.mMap.setOnMapClickListener(this);
        this.mMap.setOnMapLongClickListener(this);

        LatLng argentina = new LatLng(-37.1162298,-62.6648238);
        mMap.addMarker(new MarkerOptions().position(argentina).title("Argentina"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(argentina));
    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {
        txtLatitud.setText("" + latLng.latitude);
        txtLongitd.setText("" + latLng.longitude);

        mMap.clear();
        LatLng argentina = new LatLng(latLng.latitude,latLng.longitude);
        mMap.addMarker(new MarkerOptions().position(argentina));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(argentina));
    }

    @Override
    public void onMapLongClick(@NonNull LatLng latLng) {
        txtLatitud.setText("" + latLng.latitude);
        txtLongitd.setText("" + latLng.longitude);

        mMap.clear();
        LatLng argentina = new LatLng(latLng.latitude,latLng.longitude);
        mMap.addMarker(new MarkerOptions().position(argentina));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(argentina));
    }
}