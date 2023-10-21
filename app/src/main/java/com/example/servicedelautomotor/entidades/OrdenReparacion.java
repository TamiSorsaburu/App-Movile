package com.example.servicedelautomotor.entidades;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class OrdenReparacion {
    @PrimaryKey(autoGenerate = true)
    public long idOrden;
    public String fechaIngreso;
    public String horaIngreso;
    public String motivoIngreso;
    public String fechaSalida;
    public String horaSalida;
    public long vehiculoOrdenId;
    public long sucursalOrdenId;
}
