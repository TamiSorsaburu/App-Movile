package com.example.servicedelautomotor.entidades;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class UsuarioConAdmin {
    @Embedded
    public Usuario usuario;
    @Relation(
            parentColumn = "idUsuario",
            entityColumn = "UsuarioAdminId")
    public Administrador administrador;
}
