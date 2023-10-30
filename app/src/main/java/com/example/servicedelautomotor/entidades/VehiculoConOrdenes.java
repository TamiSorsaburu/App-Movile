package com.example.servicedelautomotor.entidades;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class VehiculoConOrdenes {
    @Embedded
    public Vehiculo vehiculo;
    @Relation(
            parentColumn = "idVehiculo",
            entityColumn = "vehiculoOrdenId"
    )
    public List<OrdenReparacion> ordenReparacionList;
}
