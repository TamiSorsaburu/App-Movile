package com.example.servicedelautomotor.entidades;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class SucursalConMecanicos {
    @Embedded
    public Sucursal sucursal;
    @Relation(
            parentColumn = "idSucursal",
            entityColumn = "sucursalMecanicoId"
    )
    public List<Mecanico> mecanicos;
}
