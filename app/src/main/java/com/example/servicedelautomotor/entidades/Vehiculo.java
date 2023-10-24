package com.example.servicedelautomotor.entidades;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Vehiculo implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public long idVehiculo;
    public String tipoVehiculo;
    public String patente;
    public String modelo;
    public String marca;

    public Vehiculo() {
    }

    public Vehiculo(String patente, String modelo, String marca) {
        this.tipoVehiculo = tipoVehiculo;
        this.patente = patente;
        this.modelo = modelo;
        this.marca = marca;
    }



}
