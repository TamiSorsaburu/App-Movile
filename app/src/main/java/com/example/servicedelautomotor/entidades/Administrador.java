package com.example.servicedelautomotor.entidades;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Administrador {
    @PrimaryKey(autoGenerate = true)
    public long idAdmin;
    public long UsuarioAdminId;
    public String nombre;
    public String apellido;
    public String telefono;
}
