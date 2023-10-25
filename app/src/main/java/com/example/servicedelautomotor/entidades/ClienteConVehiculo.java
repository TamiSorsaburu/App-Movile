package com.example.servicedelautomotor.entidades;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class ClienteConVehiculo {
    @Embedded
    public Cliente cliente;
    @Relation(
            parentColumn = "idCliente",
            entityColumn = "vehiculoClienteId")

    public List<Vehiculo> vehiculos;
}
