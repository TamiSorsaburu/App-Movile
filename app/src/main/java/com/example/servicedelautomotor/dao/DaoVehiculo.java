package com.example.servicedelautomotor.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.servicedelautomotor.entidades.Direccion;
import com.example.servicedelautomotor.entidades.Vehiculo;

import java.util.List;

@Dao
public interface DaoVehiculo {
    @Query("SELECT * FROM vehiculo")
    List<Vehiculo> obtenerVehiculoS();

    @Query("SELECT * FROM vehiculo WHERE idVehiculo=:vehi")
    Direccion obtenerVehiculo(String vehi);

    @Insert
    void insertarVehiculo(Vehiculo...vehiculos);

    @Query("UPDATE vehiculo SET tipoVehiculo=:tipoVehiculo,patente=:patente,marca=:marca,marca=:modelo WHERE idVehiculo=:vehi")
    void actualizarVehiculo(String tipoVehiculo,String patente, String marca,String modelo);

    @Query("DELETE FROM vehiculo WHERE idVehiculo=:vehi")
    void eliminarVehiculo(String vehi);
}
