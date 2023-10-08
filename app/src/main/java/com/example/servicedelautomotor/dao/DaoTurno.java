package com.example.servicedelautomotor.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;


import com.example.servicedelautomotor.entidades.Turno;

import java.util.Date;
import java.util.List;

@Dao
public interface DaoTurno {
    @Query("SELECT * FROM turno")
    List<Turno> obtenerTurnos();

    @Query("SELECT * FROM turno WHERE idTurno=:idTurno")
    Turno obtenerTurno(long idTurno);

    @Insert
    void insertarTurno(Turno...turnos);

    @Query("UPDATE turno SET dia=:dia, hora=:hora WHERE idTurno=:idTurno")
    void actualizarTurno(long idTurno, String dia, Date hora);

    @Query("DELETE FROM turno WHERE idTurno=:idTurno")
    void eliminarTurno(long idTurno);
}
