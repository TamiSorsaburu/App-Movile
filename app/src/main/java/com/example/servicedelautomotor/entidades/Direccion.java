package com.example.servicedelautomotor.entidades;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Direccion implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public long idDireccion;
    //public long direccionClienteId;
    public String calle;
    public String altura;
    public String localidad;
    public String provincia;
    public int codigoPostal;

    public Direccion( String calle, String altura, String localidad, String provincia, int codigoPostal) {
        this.calle = calle;
        this.altura = altura;
        this.localidad = localidad;
        this.provincia = provincia;
        this.codigoPostal = codigoPostal;
    }
}
