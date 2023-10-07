package com.example.servicedelautomotor.entidades;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Direccion {
    @PrimaryKey
    public long IdDirecion;
    public String calle;
    public String altura;
    public String localidad;
    public String provincia;
    public String codigoPostal;
}
