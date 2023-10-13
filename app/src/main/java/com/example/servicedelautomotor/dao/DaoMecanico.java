package com.example.servicedelautomotor.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.servicedelautomotor.entidades.Mecanico;

import java.util.List;

@Dao
public interface DaoMecanico {

    @Query("SELECT * FROM mecanico")
    List<Mecanico> obtenerMecanicos();

    @Query("SELECT * FROM mecanico WHERE idMecanico=:idMecanico")
    Mecanico obtenerMecanico(long idMecanico);

    //@Insert
    //void insertarMecanico(Mecanico...mecanicos);

    @Query("UPDATE mecanico SET nombre=:nombre,apellido=:apellido,telefono=:telefono,guardia=:guardia WHERE idMecanico=:idMecanico")
    void actualizarMecanico(long idMecanico,String nombre, String apellido,int telefono, Boolean guardia);

    @Query("DELETE FROM mecanico WHERE idMecanico=:idMecanico")
    void eliminarMecanico(long idMecanico);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertMecanicos(Mecanico... mecanicos);

    @Insert
    public void insertBothMecanicos(Mecanico mecanico1, Mecanico mecanico2);

    @Insert
    public void insertMecanicosAndFriends(Mecanico mecanico, List<Mecanico> friends);
}
