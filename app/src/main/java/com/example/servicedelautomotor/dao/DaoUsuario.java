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

    @Query("SELECT * FROM usuario WHERE correo=:correo AND contraseña=:contraseña")
    Usuario obtenerUsuario(String correo, String contraseña);

    @Insert
    void insertarUsuario(Usuario usuario);

    @Query("UPDATE usuario SET correo=:correo,contraseña=:contraseña WHERE idUsuario=:user")
    void actualizarUsuario(String user,String correo, String contraseña);

    @Query("DELETE FROM usuario WHERE idUsuario=:user")
    void eliminarUsuario(String user);

    @Query("SELECT * FROM usuario WHERE correo = :correo")
    Usuario getUsuarioPorCorreo(String correo);


}
