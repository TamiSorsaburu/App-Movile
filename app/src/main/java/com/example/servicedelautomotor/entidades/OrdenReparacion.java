package com.example.servicedelautomotor.entidades;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class OrdenReparacion {
    @PrimaryKey
    public long idOrden;
    public String fechaIngreso;
    public Date horaIngreso;
    public String motivoIngreso;
    public String fechaSalida;
    public Date horaSalida;
    public long vehiculoOrdenId;
    public long sucursalOrdenId;
}
