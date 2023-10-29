package com.example.servicedelautomotor.entidades;

import androidx.room.Entity;

@Entity (primaryKeys = {"idServicio", "idPresupuesto"})
public class ServicioPresupuestoCrossRef {
    public long idServicio;
    public long idPresupuesto;
}
