package com.example.servicedelautomotor.entidades;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Cliente {
    @PrimaryKey
    public long idCliente;
    public String nombre;
    public String apellido;
    public int telefono;
    public String imagen;


}
