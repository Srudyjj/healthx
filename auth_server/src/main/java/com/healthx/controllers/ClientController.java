package com.healthx.controllers;

import com.healthx.model.Client;
import com.healthx.service.ClientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @PostMapping
    public Client addClient(@RequestBody Client client) {
        return service.addClient(client);
    }

    @GetMapping
    public List<Client> getAllClients() {
        return service.getAllClients();
    }


}
