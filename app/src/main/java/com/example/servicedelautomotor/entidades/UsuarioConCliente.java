package com.example.servicedelautomotor.entidades;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;


public class UsuarioConCliente {
    @Embedded
    public Usuario usuario;
    @Relation(
            parentColumn = "idUsuario",
            entityColumn = "usuarioClienteId")

    public Cliente cliente;

}
