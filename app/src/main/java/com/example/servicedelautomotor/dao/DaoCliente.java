package com.example.servicedelautomotor.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.servicedelautomotor.entidades.Cliente;
import java.util.List;

@Dao
public interface DaoCliente {
    @Query("SELECT * FROM cliente")
    List<Cliente> obtenerClientes();

    @Query("SELECT * FROM cliente WHERE idCliente=:client")
    Cliente obtenerCliente(String client);

    @Insert
    void insertarCliente(Cliente...clientes);

    @Query("UPDATE cliente SET nombre=:nombre,apellido=:apellido,telefono=:telefono WHERE idCliente=:client")
    void actualizarCliente(String client,String nombre, String apellido,int telefono);

    @Query("DELETE FROM cliente WHERE idCliente=:client")
    void eliminarCliente(String client);
}
