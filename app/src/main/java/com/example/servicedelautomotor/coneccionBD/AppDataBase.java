package com.example.servicedelautomotor.coneccionBD;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.servicedelautomotor.entidades.Mecanico;
import com.example.servicedelautomotor.entidades.Presupuesto;
import com.example.servicedelautomotor.dao.DaoAdministrador;
import com.example.servicedelautomotor.dao.DaoCliente;
import com.example.servicedelautomotor.dao.DaoDireccion;
import com.example.servicedelautomotor.dao.DaoMecanico;
import com.example.servicedelautomotor.dao.DaoOrdenReparacion;
import com.example.servicedelautomotor.dao.DaoPresupuesto;
import com.example.servicedelautomotor.dao.DaoServicio;
import com.example.servicedelautomotor.dao.DaoSucursal;
import com.example.servicedelautomotor.dao.DaoTurno;
import com.example.servicedelautomotor.dao.DaoTurnoTrabajo;
import com.example.servicedelautomotor.dao.DaoUsuario;
import com.example.servicedelautomotor.dao.DaoVehiculo;
import com.example.servicedelautomotor.entidades.Administrador;
import com.example.servicedelautomotor.entidades.Cliente;
import com.example.servicedelautomotor.entidades.Direccion;
import com.example.servicedelautomotor.entidades.OrdenReparacion;
import com.example.servicedelautomotor.entidades.Servicio;
import com.example.servicedelautomotor.entidades.Sucursal;
import com.example.servicedelautomotor.entidades.Turno;
import com.example.servicedelautomotor.entidades.TurnoTrabajo;
import com.example.servicedelautomotor.entidades.Usuario;
import com.example.servicedelautomotor.entidades.Vehiculo;

@Database(entities = {
        Administrador.class,
        Cliente.class,
        Direccion.class,
        Mecanico.class,
        OrdenReparacion.class,
        Presupuesto.class,
        Servicio.class,
        Sucursal.class,
        Turno.class,
        TurnoTrabajo.class,
        Usuario.class,
        Vehiculo.class},
        version =1,exportSchema = true )
public abstract class AppDataBase extends RoomDatabase {
    public abstract DaoAdministrador daoAdministrador();
    public abstract DaoCliente daoCliente();
    public abstract DaoDireccion daoDireccion();
    public abstract DaoMecanico daoMecanico();
    public abstract DaoOrdenReparacion daoOrdenReparacion();
    public abstract DaoPresupuesto daoPresupuesto();
    public abstract DaoServicio daoServicio();
    public abstract DaoSucursal daoSucursal();
    public abstract DaoTurno daoTurno();
    public abstract DaoTurnoTrabajo daoTurnoTrabajo();
    public abstract DaoUsuario daoUsuario();
    public abstract DaoVehiculo daoVehiculo();


}
