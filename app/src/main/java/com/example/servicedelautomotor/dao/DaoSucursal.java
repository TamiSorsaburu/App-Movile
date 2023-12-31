package com.example.servicedelautomotor.dao;

import androidx.room.Dao;
import androidx.room.Embedded;
import androidx.room.Insert;
import androidx.room.Query;


import com.example.servicedelautomotor.entidades.Sucursal;

import java.util.List;

@Dao
public interface DaoSucursal {
    @Query("SELECT * FROM sucursal")
    List<Sucursal> obtenerSucursales();

    @Query("SELECT * FROM sucursal WHERE idSucursal=:idSucursal")
    Sucursal obtenerSuscursal(long idSucursal);

    @Insert
    void insertarSucursal(Sucursal...sucursales);

    @Query("UPDATE sucursal SET  nombre=:nombre, telefono=:telefono, correo=:correo, calle=:calle, altura=:altura, localidad=:localidad, provincia=:provincia, codigoPostal=:codigoPostal WHERE idSucursal=:idSucursal")
    void actualizarSucursal(long idSucursal,  String nombre,int telefono, String correo, String calle, String altura, String localidad, String provincia, int codigoPostal );

    @Query("DELETE FROM sucursal WHERE idSucursal=:idSucursal")
    void eliminarSucursal(long idSucursal);
}
