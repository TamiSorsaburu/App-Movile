package com.example.servicedelautomotor.entidades;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Sucursal implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public long idSucursal;

    public String nombre;

    public int telefono;
    public String correo;

    @Embedded public Direccion direccion;

    public Sucursal(String nombre, int telefono, String correo, Direccion direccion) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.direccion = direccion;
    }

    public String getNombre(){
        return nombre;
    }
}
