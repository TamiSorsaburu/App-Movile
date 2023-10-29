package com.example.servicedelautomotor.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.servicedelautomotor.entidades.TurnoTrabajo;

import java.util.Date;
import java.util.List;

@Dao
public interface DaoTurnoTrabajo {
    @Query("SELECT * FROM turnotrabajo")
    List<TurnoTrabajo> obtenerTurnosDeTrabajo();

    @Query("SELECT * FROM turnotrabajo WHERE idTurnoTrabajo=:idTurnoTrabajo")
    TurnoTrabajo obtenerTurnoTrabajo(long idTurnoTrabajo);

    @Insert
    void insertarTurnoTrabajo(TurnoTrabajo...turnosTrabajo);

    @Query("UPDATE turnotrabajo SET dia=:dia, hora=:hora WHERE idTurnoTrabajo=:idTurnoTrabajo")
    void actualizarTurnoTrabajo(long idTurnoTrabajo, String dia, String hora);

    @Query("DELETE FROM turnotrabajo WHERE idTurnoTrabajo=:idTurnoTrabajo")
    void eliminarTurnoTrabajo(long idTurnoTrabajo);
}
