package org.example.bll;

import org.example.dao.ClientDAO;
import org.example.model.Client;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class ClientBLL {
    private ClientDAO clientDAO = new ClientDAO();

    public Client findClientById(int clientId) {
        Client client = new Client(clientId);

        clientDAO.findById(client);

        if (client.getName() == null) {
            throw new NoSuchElementException("The client with id =" + clientId + " was not found!");
        }

        return client;
    }
    public void insertClient(String name, String mail, String address, int age) {
        clientDAO.insert(new Client(name, mail, address, age));
    }
    public void updateClient(int id, String name, String mail, String address, int age) {
        clientDAO.update(new Client(id, name, mail, address, age));
    }
    public void deleteClient(int clientId) {
        clientDAO.delete(new Client(clientId));
    }

    public ArrayList<Client> getAllClients() {
        return clientDAO.findAll();
    }
}
