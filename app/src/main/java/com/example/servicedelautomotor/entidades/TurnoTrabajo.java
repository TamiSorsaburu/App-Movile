package com.example.servicedelautomotor.entidades;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class TurnoTrabajo {
    @PrimaryKey public long idTurnoTrabajo;
    public String dia;
    public String hora;
}
