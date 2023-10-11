package com.example.servicedelautomotor.entidades;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Sucursal {
    @PrimaryKey
    public long idSucursal;

    public int telefono;
    public String correo;
}
