package com.example.servicedelautomotor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.Manifest;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class SiniestrosActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener {
    TextView txtLatitud, txtLongitd;
    GoogleMap mMap;

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private FusedLocationProviderClient fusedLocationClient;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;

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

        //Obtengo los permisos para activar la ubicacion
        Button btnActivarUbicacion = findViewById(R.id.btnActivarUbicacion);
        btnActivarUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkLocationPermission();

                // Comienza a recibir actualizaciones de ubicación
                if (ContextCompat.checkSelfPermission(SiniestrosActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);
                }
            }
        });


        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        locationRequest = new LocationRequest();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(5000); // Intervalo de actualización de ubicación en milisegundos

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                for (android.location.Location location : locationResult.getLocations()) {
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();
                    String locationText = "Latitud: " + latitude + ", Longitud: " + longitude;
                    txtLatitud.setText(locationText);

                    Geocoder geocoder = new Geocoder(SiniestrosActivity.this, Locale.getDefault());
                    List<Address> addresses = null;
                    try {
                        addresses = geocoder.getFromLocation(latitude, longitude, 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (addresses != null && !addresses.isEmpty()) {
                        String address = addresses.get(0).getAddressLine(0);
                        txtLatitud.setText(address);
                    } else {
                        txtLatitud.setText("Dirección no encontrada");
                    }

                }
            }
        };

    }
    @Override
    protected void onPause() {
        super.onPause();
        // Detener las actualizaciones de ubicación si la actividad está en pausa
        fusedLocationClient.removeLocationUpdates(locationCallback);
    }

    private void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            // Permiso de ubicación ya concedido, puedes obtener la ubicación aquí.
            // Añade el código para obtener la ubicación y mostrarla en TextUbicacion.
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permiso de ubicación concedido, puedes obtener la ubicación aquí.
                // Añade el código para obtener la ubicación y mostrarla en TextUbicacion.
            } else {
                // Permiso de ubicación denegado, puedes mostrar un mensaje o tomar medidas adicionales si es necesario.
            }
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

    public void btn(View V) {
        Intent confirma = new Intent(this, activity_confirma_ayuda.class);
        startActivity(confirma);

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        this.mMap.setOnMapClickListener(this);
        this.mMap.setOnMapLongClickListener(this);

        LatLng taller1 = new LatLng(-34.9204309,-57.9943697);
        mMap.addMarker(new MarkerOptions().position(taller1).title("Taller 1"));

        LatLng taller2 = new LatLng(-34.6538517,-58.5787489);
        mMap.addMarker(new MarkerOptions().position(taller2).title("Taller 2"));

        LatLng taller3 = new LatLng(-31.3992803,-66.4111979);
        mMap.addMarker(new MarkerOptions().position(taller3).title("Taller 3"));

        LatLng taller4 = new LatLng(-34.6274155,-68.3664091);
        mMap.addMarker(new MarkerOptions().position(taller4).title("Taller 4"));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(taller1));
    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {
//        txtLatitud.setText("" + latLng.latitude);
//        txtLongitd.setText("" + latLng.longitude);

        LatLng argentina = new LatLng(latLng.latitude,latLng.longitude);
        mMap.addMarker(new MarkerOptions().position(argentina));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(argentina));
    }

    @Override
    public void onMapLongClick(@NonNull LatLng latLng) {
//        txtLatitud.setText("" + latLng.latitude);
//        txtLongitd.setText("" + latLng.longitude);

        LatLng argentina = new LatLng(latLng.latitude,latLng.longitude);
        mMap.addMarker(new MarkerOptions().position(argentina));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(argentina));
    }
    public void verVideo(View V) {
        Intent intent = new Intent(this, activity_video.class);
        startActivity(intent);
    }
}



