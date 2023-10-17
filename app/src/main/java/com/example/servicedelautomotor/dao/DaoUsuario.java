package com.example.servicedelautomotor.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.servicedelautomotor.entidades.Cliente;
import com.example.servicedelautomotor.entidades.Usuario;

import java.util.List;

@Dao
public interface DaoUsuario {
    @Query("SELECT * FROM usuario")
    List<Usuario> obtenerUsuarios();

    @Query("SELECT * FROM usuario WHERE idUsuario=:user")
    Usuario obtenerUsuario(String user);

    @Insert
    void insertarUsuario(Usuario...usuarios);

    @Query("UPDATE usuario SET correo=:correo,contraseña=:contraseña WHERE idUsuario=:user")
    void actualizarUsuario(String user,String correo, String contraseña);

    @Query("DELETE FROM usuario WHERE idUsuario=:user")
    void eliminarUsuario(String user);
}
