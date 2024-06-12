package com.devsu.accountOperation.infraestructure.controller;

import com.devsu.accountOperation.application.service.OperationService;
import com.devsu.accountOperation.infraestructure.vo.OperationVO;
import com.devsu.accountOperation.util.exception.InfoNotFoundException;
import com.devsu.accountOperation.util.exception.InvalidOperationException;
import com.devsu.accountOperation.util.rest.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class OperationController {
    @Autowired
    private OperationService service;
    @GetMapping("/movimientos")
    public ResponseEntity<?> getAllOperations() {
        return ResponseEntity.ok(service.getAllOperations());
    }

    @GetMapping("/movimientos/{id}")
    public ResponseEntity<?> getOperationById(@PathVariable Integer id) throws InfoNotFoundException {
        return ResponseEntity.ok(service.getOperationById(id));
    }

    @RequestMapping(value = "movimientos", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> createOperation(@RequestBody OperationVO operationRequest) throws InvalidOperationException, InfoNotFoundException {
        service.createOperation(operationRequest);
        Response<String> response = new Response<>(HttpStatus.OK.value(), "Entidad creada con éxito");
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "movimientos", method = RequestMethod.PATCH)
    @ResponseBody
    public ResponseEntity<?> updateOperation(@RequestBody OperationVO operationRequest) throws InfoNotFoundException {
        service.updateOperation(operationRequest);
        Response<String> response = new Response<>(HttpStatus.OK.value(), "Entidad modificada con éxito");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/movimientos/{id}")
    public ResponseEntity<?> cancelOperation(@PathVariable Integer id) throws InfoNotFoundException {
        service.cancelOperation(id);
        Response<String> response = new Response<>(HttpStatus.OK.value(), "Entidad eliminada con éxito");
        return ResponseEntity.ok(response);
    }
}