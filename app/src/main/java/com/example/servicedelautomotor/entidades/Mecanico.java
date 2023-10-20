package com.example.servicedelautomotor.entidades;

import android.content.Intent;

import androidx.annotation.InspectableProperty;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;


@Entity
public class Mecanico {

    @PrimaryKey(autoGenerate = true)
    public int idMecanico;

    public String nombre;

    public String apellido;

    public int telefono;
    public String guardia;
    public long sucursalMecanicoId;


    public Mecanico() {
    }

    public Mecanico(String nombre, String apellido, int telefono, String guardia, int sucursalMecanicoId) {
        this.idMecanico = idMecanico;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.guardia = guardia;
        this.sucursalMecanicoId = sucursalMecanicoId;
    }

    @Ignore
    public Mecanico(int idMecanico) {
        this.idMecanico = idMecanico;
    }


    public int getIdMecanico() {
        return idMecanico;
    }

    public void setIdMecanico(int idMecanico) {
        this.idMecanico = idMecanico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getGuardia() {
        return guardia;
    }

    public void setGuardia(String guardia) {
        this.guardia = guardia;
    }

    public long getSucursalMecanicoId() {
        return sucursalMecanicoId;
    }

    public void setSucursalMecanicoId(long sucursalMecanicoId) {
        this.sucursalMecanicoId = sucursalMecanicoId;
    }

    @Override
    public String toString() {
        return "Mecanico{" +
                "idMecanico=" + idMecanico +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", telefono=" + telefono +
                ", guardia='" + guardia + '\'' +
                ", sucursalMecanicoId=" + sucursalMecanicoId +
                '}';
    }
}
