package com.example.servicedelautomotor.entidades;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Vehiculo implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public long idVehiculo;
    public String tipoVehiculo;
    public String patente;
    public String modelo;
    public String marca;


    public Vehiculo() {
    }

    public Vehiculo(String patente,String tipoVehiculo, String modelo, String marca) {
        this.tipoVehiculo = tipoVehiculo;
        this.patente = patente;
        this.modelo = modelo;
        this.marca = marca;
    }


    public long getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(long idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Override
    public String toString() {
        return "Vehiculo{" +
                "idVehiculo=" + idVehiculo +
                ", tipoVehiculo='" + tipoVehiculo + '\'' +
                ", patente='" + patente + '\'' +
                ", modelo='" + modelo + '\'' +
                ", marca='" + marca + '\'' +
                '}';
    }
}
