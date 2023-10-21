package com.example.servicedelautomotor.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.servicedelautomotor.entidades.Mecanico;

import java.util.List;

@Dao
public interface DaoMecanico {

    @Insert
    void insertarMecanico(Mecanico... mecanico);

    @Query("SELECT EXISTS(SELECT * FROM mecanico WHERE idMecanico=:idMecanico)")
    Boolean obtenerMecanico(int idMecanico);


    @Query("SELECT * FROM mecanico")
    List<Mecanico> obtenerMecanicos();




    @Query("UPDATE mecanico SET nombre=:nombre,apellido=:apellido,telefono=:telefono ,guardia=:guardia,sucursalMecanicoId=:sucursal WHERE idMecanico=:idMecanico")
    void actualizarMecanico(String nombre, String apellido, int telefono, String guardia,int sucursal, int idMecanico);


    @Delete
    void eliminarMecanico(Mecanico mecanico);
}
