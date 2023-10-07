package com.example.servicedelautomotor.entidades;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class ClienteConTurnos {
    @Embedded
    public Cliente cliente;
    @Relation(
            parentColumn = "idCliente",
            entityColumn = "clienteTurnoId"
    )
    public List<Turno> turnos;
}
