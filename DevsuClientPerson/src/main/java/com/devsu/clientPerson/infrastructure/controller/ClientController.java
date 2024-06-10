package com.devsu.clientPerson.infrastructure.controller;
import com.devsu.clientPerson.application.service.ClientService;
import com.devsu.clientPerson.infrastructure.vo.ClientResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ClientController {
    @Autowired
    private ClientService service;
    @GetMapping("/clientes")
    public List<ClientResponse> getAllClients() throws IOException {
        return service.getAllClients();
    }
}