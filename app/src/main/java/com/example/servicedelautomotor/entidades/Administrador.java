package com.example.servicedelautomotor.entidades;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Administrador {
    @PrimaryKey public long idAdmin;
    public String nombre;
    public String apellido;
    public String telefono;
}
