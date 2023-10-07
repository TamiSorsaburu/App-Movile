package com.example.servicedelautomotor.entidades;

import android.content.Intent;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;


@Entity
public class Mecanico {
    @PrimaryKey public long idMecanico;
    public String nombre;
    public String apellido;
    public Integer telefono;
    public Boolean guardia;
    public String turnoTrabajo;

}
