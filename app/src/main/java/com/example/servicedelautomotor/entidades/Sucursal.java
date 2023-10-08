package com.example.servicedelautomotor.entidades;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Sucursal {
    @PrimaryKey
    public long idSucursal;
    @Embedded
    public Direccion direccion;
    public int telefono;
    public String correo;
}
