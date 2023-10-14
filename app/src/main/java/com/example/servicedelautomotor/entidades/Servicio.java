package com.example.servicedelautomotor.entidades;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Servicio {
    @PrimaryKey
    public long idServicio;
    public String nombre;
    public String descripcion;
    public double precio;
}