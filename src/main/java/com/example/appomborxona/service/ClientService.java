package com.example.appomborxona.service;

import com.example.appomborxona.entity.Client;
import com.example.appomborxona.payload.Result;
import com.example.appomborxona.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    ClientRepository clientRepository;

    public Result add(Client client) {

        // Exists client by phone number
        boolean exists = clientRepository.existsByPhoneNumber(client.getPhoneNumber());
        if (exists)
            return new Result("Phone number already exists", false);

        // Add client
        clientRepository.save(client);
        return new Result("Client added", true);
    }


    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    public Optional<Client> get(Integer id) {
        return clientRepository.findById(id);
    }

    public Result edit(Integer id, Client client) {

        // Find client
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isEmpty())
            return new Result("Client not found", false);

        // Edit client
        Client editingClient = optionalClient.get();
        editingClient.setName(client.getName());
        editingClient.setPhoneNumber(client.getPhoneNumber());
        clientRepository.save(editingClient);
        return new Result("Client edited", true);
    }

    public Result delete(Integer id) {
        clientRepository.deleteById(id);
        return new Result("Client deleted", true);
    }
}