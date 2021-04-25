package com.healthx.service;

import com.healthx.exeptions.ClientAlreadyExistsException;
import com.healthx.model.Client;
import com.healthx.repository.ClientRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository repository;
    private final PasswordEncoder passwordEncoder;

    public ClientService(ClientRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public Client addClient(Client client) {
        Optional<Client> existedClient = repository.findById(client.getId());
        if (existedClient.isPresent()) {
            throw new ClientAlreadyExistsException("Client already exists.");
        }

        client.setSecret(passwordEncoder.encode(client.getSecret()));
        client.getGrantTypes().forEach(gt -> gt.setClient(client));
        repository.save(client);
        return client;
    }

    public List<Client> getAllClients() {
        return repository.findAll();
    }
}
