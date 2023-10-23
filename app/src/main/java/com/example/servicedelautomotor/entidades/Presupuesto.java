package com.example.servicedelautomotor.entidades;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Presupuesto {
    @PrimaryKey(autoGenerate = true)
    public long idPresupuesto;
    public double total;

}
