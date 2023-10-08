package com.example.servicedelautomotor.entidades;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class SucursalConOrdenes {
    @Embedded
    public Sucursal sucursal;
    @Relation(
            parentColumn = "idSucursal",
            entityColumn = "sucursalOrdenId"
    )
    public List<OrdenReparacion> ordenReparacionList;
}
