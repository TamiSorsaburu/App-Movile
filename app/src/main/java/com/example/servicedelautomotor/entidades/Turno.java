package com.example.servicedelautomotor.entidades;

import androidx.room.PrimaryKey;

import java.util.Date;

public class Turno {
    @PrimaryKey
    public long idTurno;
    public long clienteTurnoId;
    public String dia;
    public Date hora;
}
