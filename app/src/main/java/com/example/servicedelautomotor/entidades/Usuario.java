package com.example.servicedelautomotor.entidades;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Usuario {
    @PrimaryKey
    public long idUsuario;
    public String correo;
    public String contraseña;

    public Usuario(long idUsuario, String correo, String contraseña) {
        this.idUsuario = idUsuario;
        this.correo = correo;
        this.contraseña = contraseña;
    }
}
