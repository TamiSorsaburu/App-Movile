package com.example.servicedelautomotor.crud;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.servicedelautomotor.dao.DaoCliente;
import com.example.servicedelautomotor.dao.DaoCliente_Impl;
import com.example.servicedelautomotor.entidades.Cliente;

public class ClienteViewModel extends AndroidViewModel {
    private LiveData<Cliente>clienteLiveData;
    private ClienteRepositorio clienteRepositorio;
    public DaoCliente daoCliente;


    public ClienteViewModel(@NonNull Application application) {
        super(application);
        clienteRepositorio=new ClienteRepositorio(application);
        clienteLiveData=clienteRepositorio.getClienteLiveData();
    }

    public LiveData<Cliente>getClienteLiveData(){
        return clienteLiveData;
    }
}
