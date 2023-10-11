package com.example.servicedelautomotor.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.servicedelautomotor.entidades.Presupuesto;


import java.util.Date;
import java.util.List;

@Dao public interface DaoPresupuesto {
    @Query("SELECT * FROM presupuesto")
    List<Presupuesto> obtenerPresupuestos();

    @Query("SELECT * FROM presupuesto WHERE idPresupuesto=:idPresupuesto")
    Presupuesto obtenerPresupuesto(long idPresupuesto);

    @Insert
    void insertarPresupuesto(Presupuesto...presupuestos);

    @Query("UPDATE presupuesto SET total=:total WHERE idPresupuesto=:idPresupuesto")
    void actualizarOrden(long idPresupuesto, Double total );

    @Query("DELETE FROM presupuesto WHERE idPresupuesto=:idPresupuesto")
    void eliminarPresupuesto(long idPresupuesto);
}
