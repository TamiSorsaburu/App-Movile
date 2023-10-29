package com.example.servicedelautomotor.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.servicedelautomotor.entidades.Cliente;
import com.example.servicedelautomotor.entidades.Direccion;

import java.util.List;

@Dao
public interface DaoDireccion {
    @Query("SELECT * FROM direccion")
    List<Direccion> obtenerDirecciones();

    @Query("SELECT * FROM direccion WHERE idDireccion=:direcc")
    Direccion obtenerDireccion(long direcc);

    @Insert
    void insertarDireccion(Direccion...direccion);

    @Query("UPDATE direccion SET calle=:calle,altura=:altura,localidad=:localidad,provincia=:provincia,codigoPostal=:codigoPostal WHERE idDireccion=:idDireccion")
    void actualizarDireccion(long idDireccion, String calle,int altura, String localidad,String provincia,int codigoPostal);

    @Query("DELETE FROM direccion WHERE idDireccion=:direcc")
    void eliminarDireccion(String direcc);
}
