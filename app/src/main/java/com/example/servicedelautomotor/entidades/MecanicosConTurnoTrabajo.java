package com.example.servicedelautomotor.entidades;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class MecanicosConTurnoTrabajo {
    @Embedded public TurnoTrabajo turnoTrabajo;
    @Relation(
            parentColumn = "idTurnoTrabajo",
            entityColumn = "idMecanico",
            associateBy = @Junction(MecanicoTurnoTrabajoCrossRef.class)
    )
    public List<Mecanico> mecanicos;
}
