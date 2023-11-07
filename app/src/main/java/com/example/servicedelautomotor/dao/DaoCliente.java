package com.example.servicedelautomotor.dao;

import androidx.lifecycle.LiveData;
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

    @Query("SELECT * FROM cliente LIMIT 1")
    LiveData<Cliente>getCurrentCliente();

    @Insert
    void insertarCliente(Cliente...clientes);

    @Query("UPDATE cliente SET nombre=:nombre,apellido=:apellido,imagen=:imagen,telefono=:telefono, usuarioClienteId=:usuarioClienteId,calle=:calle,altura=:altura,localidad=:localidad,provincia=:provincia,codigoPostal=:postal,modelo=:modelo,marca=:marca,patente=:patente WHERE idCliente=:client")
    void actualizarCliente(int client,String nombre, String apellido,String imagen,int telefono,int usuarioClienteId, String calle,String altura,String localidad,String provincia,int postal,String modelo,String marca,String patente);

    @Query("DELETE FROM cliente WHERE idCliente=:client")
    void eliminarCliente(long client);
}
