package com.example.servicedelautomotor.entidades;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;

/*@Entity(tableName = "Cliente",foreignKeys = @ForeignKey(
        entity = Usuario.class,
        parentColumns = "idUsuario",
        childColumns = "usuarioClienteId"
))*/
@Entity public class Cliente implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int idCliente;

    public String nombre;
    public String apellido;
    public int telefono;
    public String imagen;


    @Embedded
    public Direccion direccion;

    @Embedded
    public Vehiculo Vehiculo;


    public Cliente() {
    }

    public Cliente(String nombre, String apellido, int telefono,String imagen, Direccion direccion, Vehiculo vehiculo) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.imagen = imagen;
        this.direccion = direccion;
        Vehiculo = vehiculo;
    }




    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public Vehiculo getVehiculo() {
        return Vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        Vehiculo = vehiculo;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "idCliente=" + idCliente +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", telefono=" + telefono +
                ", imagen='" + imagen + '\'' +
                ", direccion=" + direccion +
                ", Vehiculo=" + Vehiculo +
                '}';
    }


}
