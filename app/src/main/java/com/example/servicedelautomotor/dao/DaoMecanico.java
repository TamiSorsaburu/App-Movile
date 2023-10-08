package com.example.servicedelautomotor.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.servicedelautomotor.Mecanico;

import java.util.List;

@Dao
public interface DaoMecanico {

    @Query("SELECT * FROM mecanico")
    List<Mecanico> obtenerMecanicos();

    @Query("SELECT * FROM mecanico WHERE idMecanico=:mecanic")
    Mecanico obtenerMecanico(String mecanic);

    @Insert
    void insertarMecanico(Mecanico...mecanicos);

    @Query("UPDATE mecanico SET nombre=:nombre,apellido=:apellido,telefono=:telefono,guardia=:guardia,turnoTrabajo=:turnoTrabajo WHERE idMecanico=:mecanic")
    void actualizarMecanico(String mecanic,String nombre, String apellido,int telefono, Boolean guardia, String turnoTrabajo);

    @Query("DELETE FROM mecanico WHERE idMecanico=:mecanic")
    void eliminarMecanico(String mecanic);
}
