package com.example.servicedelautomotor.entidades;

import androidx.room.Embedded;
import androidx.room.Relation;

public class ClienteConDireccion {
    @Embedded
    public Cliente cliente;
    @Relation(
            parentColumn = "idCliente",
            entityColumn = "direccionClienteId")

    public Direccion direccion;
}
