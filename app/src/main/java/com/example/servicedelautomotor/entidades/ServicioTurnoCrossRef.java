package com.example.servicedelautomotor.entidades;

import androidx.room.Entity;

@Entity(primaryKeys = {"idServicio", "idTurno"})
public class ServicioTurnoCrossRef {
    public long idServicio;
    public long idTurno;

}
