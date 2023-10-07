package com.example.servicedelautomotor.entidades;

import android.content.Intent;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;


@Entity
public class Mecanico {

    @PrimaryKey
    public Integer idMecanico;

    @ColumnInfo(name="nombre")
    public String nombre;
    @ColumnInfo(name="apellido")
    public String apellido;
    @ColumnInfo(name="telefono")
    public Integer telefono;
    @ColumnInfo(name="guardia")
    public Boolean guardia;
    @ColumnInfo(name="turno_trabajo")
    public String[] turnoTrabajo;

}
