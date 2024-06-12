package com.devsu.clientPerson.infrastructure.controller;
import com.devsu.clientPerson.application.service.ClientService;
import com.devsu.clientPerson.infrastructure.vo.ClientVO;
import com.devsu.clientPerson.util.exception.InfoNotFoundException;
import com.devsu.clientPerson.util.rest.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class ClientController {
    @Autowired
    private ClientService service;
    @GetMapping("/clientes")
    public ResponseEntity<?> getAllClients() throws IOException {
        return ResponseEntity.ok(service.getAllClients());
    }

    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable Integer id) throws IOException {
        service.deleteClient(id);
        Response<String> response = new Response<>(HttpStatus.OK.value(), "Entidad eliminada con éxito");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity<?> getClient(@PathVariable Integer id) throws IOException {
        return ResponseEntity.ok(service.getClientByPersonId(id));
    }

    @RequestMapping(value = "clientes", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> createClient(@RequestBody ClientVO clientRequest) throws InfoNotFoundException {
        service.createClient(clientRequest);
        Response<String> response = new Response<>(HttpStatus.OK.value(), "Entidad creada con éxito");
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "clientes", method = RequestMethod.PATCH)
    @ResponseBody
    public ResponseEntity<?> updateClient(@RequestBody ClientVO clientRequest) throws InfoNotFoundException {
        service.updateClient(clientRequest);
        Response<String> response = new Response<>(HttpStatus.OK.value(), "Entidad modificada con éxito");
        return ResponseEntity.ok(response);
    }
}