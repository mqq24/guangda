package com.example.guangda.mapper;

import com.example.guangda.entity.Client;

import java.util.List;

public interface ClientMapper {
    List<Client> selectClientList(Client client);
    Client gatselectClientList(int PreCustomerNo);
    void addClient(Client client);
    void deleteClient(int precustomerno);
    void updateByPrimaryKey(Client client);
}