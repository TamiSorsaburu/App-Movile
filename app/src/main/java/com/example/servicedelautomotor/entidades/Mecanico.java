package com.example.servicedelautomotor.entidades;

import android.content.Intent;

import androidx.annotation.InspectableProperty;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;


@Entity
public class Mecanico {

    @PrimaryKey
    public long idMecanico;
    public String nombre;

    public String apellido;

    public Integer telefono;

    public Boolean guardia;

    public long sucursalMecanicoId;

    public Mecanico(long idMecanico, String nombre, String apellido, Integer telefono, Boolean guardia) {
        this.idMecanico = idMecanico;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.guardia = guardia;
    }
}
