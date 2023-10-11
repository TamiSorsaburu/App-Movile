package com.example.servicedelautomotor.entidades;

import androidx.room.Entity;

@Entity(primaryKeys = {"idMecanico", "idTurnoTrabajo"})
public class MecanicoTurnoTrabajoCrossRef {
    public long idMecanico;
    public long idTurnoTrabajo;

}
