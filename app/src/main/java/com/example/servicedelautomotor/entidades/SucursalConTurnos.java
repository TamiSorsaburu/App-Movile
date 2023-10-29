package com.example.servicedelautomotor.entidades;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class SucursalConTurnos {
    @Embedded
    public Sucursal sucursal;
    @Relation(
            parentColumn = "idSucursal",
            entityColumn = "sucursalTurnoId"
    )
    public List<Turno> turnos;
}
