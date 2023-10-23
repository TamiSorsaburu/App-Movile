package com.example.servicedelautomotor.entidades;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Cliente {
    @PrimaryKey(autoGenerate = true)
    public long idCliente;
    public long usuarioClienteId;
    public String nombre;
    public String apellido;
    public int telefono;
    public String imagen;

    @Embedded
    public Direccion direccion;


}
