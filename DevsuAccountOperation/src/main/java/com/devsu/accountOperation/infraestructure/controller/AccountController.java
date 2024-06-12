package com.devsu.accountOperation.infraestructure.controller;

import com.devsu.accountOperation.application.service.AccountService;
import com.devsu.accountOperation.infraestructure.rest.ClientREST;
import com.devsu.accountOperation.infraestructure.vo.AccountVO;
import com.devsu.accountOperation.infraestructure.vo.ClientVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class AccountController {
    @Autowired
    private AccountService service;

    private final ClientREST clientREST;

    public AccountController(ClientREST clientREST) {
        this.clientREST = clientREST;
    }

    @GetMapping("/cuentas")
    public List<AccountVO> getAllAccounts() throws IOException {
        return service.getAllAccounts();
    }

    @GetMapping("/cuentas/{id}")
    public AccountVO getAccount(@PathVariable Integer id) throws IOException {
        return service.getAccountById(id);
    }

    @RequestMapping(value = "cuentas", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> createAccount(@RequestBody AccountVO accountVORequest) throws IOException {
        service.createAccount(accountVORequest);
        ClientVO client=clientREST.getClient(accountVORequest.getClientId());
        return ResponseEntity.ok("Entidad creada con éxito");
    }

    @RequestMapping(value = "cuentas", method = RequestMethod.PATCH)
    @ResponseBody
    public ResponseEntity<?> updateAccount(@RequestBody AccountVO accountVORequest) throws IOException {
        service.updateAccount(accountVORequest);
        ClientVO client=clientREST.getClient(accountVORequest.getClientId());
        return ResponseEntity.ok("Entidad creada con éxito");
    }

    @DeleteMapping("/cuentas/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Integer id) throws IOException {
        service.deleteAccount(id);
        return ResponseEntity.ok("Entidad eliminada con éxito");
    }
}