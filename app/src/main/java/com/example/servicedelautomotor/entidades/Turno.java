package com.example.servicedelautomotor.entidades;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Turno {
    @PrimaryKey
    public long idTurno;
    public long clienteTurnoId;
    public long sucursalTurnoId;
    public String dia;
    public String hora;
}
