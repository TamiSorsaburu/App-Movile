package com.example.servicedelautomotor.entidades;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class PresupuestoConServicios {
    @Embedded public Presupuesto presupuesto;
    @Relation(
            parentColumn = "idPresupuesto",
            entityColumn = "idServicio",
            associateBy = @Junction(ServicioPresupuestoCrossRef.class)
    )
    public List<Servicio> servicios;
}
