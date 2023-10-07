package com.example.servicedelautomotor.entidades;

import androidx.room.Embedded;

import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class TurnoConServicios {
    @Embedded
    public Turno turno;
    @Relation(
            parentColumn = "idTurno",
            entityColumn = "idServicio",
            associateBy = @Junction(ServicioTurnoCrossRef.class)

    )
    public List<Servicio> servicios;
}
