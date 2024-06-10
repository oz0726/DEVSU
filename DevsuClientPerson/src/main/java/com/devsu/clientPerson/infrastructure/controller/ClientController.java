package com.devsu.clientPerson.infrastructure.controller;
import com.devsu.clientPerson.application.service.ClientService;
import com.devsu.clientPerson.infrastructure.vo.ClientRequest;
import com.devsu.clientPerson.infrastructure.vo.ClientResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class ClientController {
    @Autowired
    private ClientService service;
    @GetMapping("/clientes")
    public List<ClientResponse> getAllClients() throws IOException {
        return service.getAllClients();
    }

    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable Integer id) throws IOException {
        service.deleteClient(id);
        return ResponseEntity.ok("Entidad eliminada con éxito");
    }

    @GetMapping("/clientes/{id}")
    public ClientResponse getClient(@PathVariable Integer id) throws IOException {
        return service.getClientByPersonId(id);
    }

    @RequestMapping(value = "clientes", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> createClient(@RequestBody ClientRequest clientRequest) throws IOException {
        service.createClient(clientRequest);
        return ResponseEntity.ok("Entidad creada con éxito");
    }

    @RequestMapping(value = "clientes", method = RequestMethod.PATCH)
    @ResponseBody
    public ResponseEntity<?> updateClient(@RequestBody ClientRequest clientRequest) throws IOException {
        service.updateClient(clientRequest);
        return ResponseEntity.ok("Entidad creada con éxito");
    }
}