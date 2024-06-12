package com.devsu.accountOperation.infraestructure.controller;

import com.devsu.accountOperation.application.service.AccountService;
import com.devsu.accountOperation.infraestructure.rest.ClientREST;
import com.devsu.accountOperation.infraestructure.vo.AccountVO;
import com.devsu.accountOperation.util.exception.InfoNotFoundException;
import com.devsu.accountOperation.util.rest.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {
    @Autowired
    private AccountService service;

    private final ClientREST clientREST;

    public AccountController(ClientREST clientREST) {
        this.clientREST = clientREST;
    }

    @GetMapping("/cuentas")
    public ResponseEntity<?>getAllAccounts() {
        return ResponseEntity.ok(service.getAllAccounts());
    }

    @GetMapping("/cuentas/{id}")
    public ResponseEntity<?> getAccount(@PathVariable Integer id) throws InfoNotFoundException {
        return ResponseEntity.ok(service.getAccountById(id));
    }

    @RequestMapping(value = "cuentas", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> createAccount(@RequestBody AccountVO accountVORequest) throws InfoNotFoundException {
        clientREST.getClient(accountVORequest.getClientId());
        service.createAccount(accountVORequest);
        Response<String> response = new Response<>(HttpStatus.OK.value(), "Entidad creada con éxito");
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "cuentas", method = RequestMethod.PATCH)
    @ResponseBody
    public ResponseEntity<?> updateAccount(@RequestBody AccountVO accountVORequest) throws InfoNotFoundException {
        if(accountVORequest.getClientId()!=0)clientREST.getClient(accountVORequest.getClientId());
        service.updateAccount(accountVORequest);
        Response<String> response = new Response<>(HttpStatus.OK.value(), "Entidad modificada con éxito");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/cuentas/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable Integer id) throws InfoNotFoundException {
        service.deleteAccount(id);
        Response<String> response = new Response<>(HttpStatus.OK.value(), "Entidad eliminada con éxito");
        return ResponseEntity.ok(response);
    }
}