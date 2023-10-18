package com.example.servicedelautomotor.entidades;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Vehiculo {
    @PrimaryKey(autoGenerate = true)
    public long idVehiculo;
    public String tipoVehiculo;
    public int patente;
    public String modelo;
    public String marca;

}
