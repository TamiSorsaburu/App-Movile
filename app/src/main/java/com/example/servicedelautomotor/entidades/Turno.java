package com.example.servicedelautomotor.entidades;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Turno {
    @PrimaryKey(autoGenerate = true)
    public long idTurno;
    public long clienteTurnoId;
    public long sucursalTurnoId;
    public String dia;
    public String hora;
    public String precio;
    public String servicios;
}
