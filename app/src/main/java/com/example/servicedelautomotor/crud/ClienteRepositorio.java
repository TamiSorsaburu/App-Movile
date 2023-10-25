package com.example.servicedelautomotor.crud;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.servicedelautomotor.coneccionBD.AppDataBase;
import com.example.servicedelautomotor.dao.DaoCliente;
import com.example.servicedelautomotor.entidades.Cliente;

public class ClienteRepositorio {
    private DaoCliente daoCliente;
    private LiveData<Cliente>clienteLiveData;

    public ClienteRepositorio(Application application){
        AppDataBase appDataBase=AppDataBase.getInstance(application);

        daoCliente= appDataBase.daoCliente();
        clienteLiveData=daoCliente.getCurrentCliente();
    }

    public LiveData<Cliente>getClienteLiveData(){
        return clienteLiveData;
    }
}
