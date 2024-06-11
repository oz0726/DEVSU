package com.devsu.accountOperation.infraestructure.controller;

import com.devsu.accountOperation.application.service.AccountService;
import com.devsu.accountOperation.infraestructure.vo.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class AccountController {
    @Autowired
    private AccountService service;
    @GetMapping("/cuentas")
    public List<Account> getAllAccounts() throws IOException {
        return service.getAllAccounts();
    }

    @DeleteMapping("/cuentas/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Integer id) throws IOException {
        service.deleteAccount(id);
        return ResponseEntity.ok("Entidad eliminada con éxito");
    }

    @GetMapping("/cuentas/{id}")
    public Account getAccount(@PathVariable Integer id) throws IOException {
        return service.getAccountById(id);
    }

    @RequestMapping(value = "cuentas", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> createAccount(@RequestBody Account accountRequest) throws IOException {
        service.createAccount(accountRequest);
        return ResponseEntity.ok("Entidad creada con éxito");
    }

    @RequestMapping(value = "cuentas", method = RequestMethod.PATCH)
    @ResponseBody
    public ResponseEntity<?> updateAccount(@RequestBody Account accountRequest) throws IOException {
        service.updateAccount(accountRequest);
        return ResponseEntity.ok("Entidad creada con éxito");
    }
}