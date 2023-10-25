package com.example.servicedelautomotor.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;


import com.example.servicedelautomotor.entidades.Servicio;

import java.util.List;

@Dao
public interface DaoServicio {
    @Query("SELECT * FROM servicio")
    List<Servicio> obtenerServicios();

    @Query("SELECT * FROM servicio WHERE idServicio=:idServicio")
    Servicio obtenerServicio(long idServicio);

    @Insert
    void insertarServicio(Servicio...servicios);

    @Query("UPDATE servicio SET nombre=:nombre, descripcion=:descripcion, precio=:precio WHERE idServicio=:idServicio")
    void actualizarServicio(long idServicio, String nombre, String descripcion, Double precio );

    @Query("DELETE FROM servicio WHERE idServicio=:idServicio")
    void eliminarServicio(long idServicio);
}
