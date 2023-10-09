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
    List<Sucursal> obtenerSucursal();

    @Query("SELECT * FROM sucursal WHERE idSucursal=:idSucursal")
    Sucursal obtenerSuscursal(long idSucursal);

    @Insert
    void insertarSucursal(Sucursal...sucursales);

    @Query("UPDATE sucursal SET  telefono=:telefono, correo=:correo WHERE idSucursal=:idSucursal")
    void actualizarSucursal(long idSucursal,  int telefono, String correo );

    @Query("DELETE FROM sucursal WHERE idSucursal=:idSucursal")
    void eliminarSucursal(long idSucursal);
}
