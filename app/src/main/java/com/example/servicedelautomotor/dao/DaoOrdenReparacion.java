package com.example.servicedelautomotor.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.servicedelautomotor.entidades.OrdenReparacion;


import java.util.Date;
import java.util.List;

@Dao
public interface DaoOrdenReparacion {
    @Query("SELECT * FROM ordenreparacion")
    List<OrdenReparacion> obtenerOrdenes();

    @Query("SELECT * FROM ordenreparacion WHERE idOrden=:orden")
    OrdenReparacion obtenerOrden(long orden);

    @Insert
    void insertarOrden(OrdenReparacion...ordenes);

    @Query("UPDATE ordenreparacion SET fechaIngreso=:fechaIngreso,horaIngreso=:horaIngreso, motivoIngreso=:motivoIngreso, fechaSalida=:fechaSalida, horaSalida=:horaSalida WHERE idOrden=:idOrden")
    void actualizarOrden(long idOrden, String fechaIngreso, Date horaIngreso, String motivoIngreso, String fechaSalida, Date horaSalida );

    @Query("DELETE FROM ordenreparacion WHERE idOrden=:idOrden")
    void eliminarOrden(long idOrden);
}
