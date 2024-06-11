package com.devsu.accountOperation.infraestructure.controller;

import com.devsu.accountOperation.application.service.OperationService;
import com.devsu.accountOperation.infraestructure.vo.OperationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class OperationController {
    @Autowired
    private OperationService service;
    @GetMapping("/movimientos")
    public List<OperationVO> getAllOperations() throws IOException {
        return service.getAllOperations();
    }

    @GetMapping("/movimientos/{id}")
    public OperationVO getOperationById(@PathVariable Integer id) throws IOException {
        return service.getOperationById(id);
    }

    @RequestMapping(value = "movimientos", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> createOperation(@RequestBody OperationVO operationRequest) throws IOException {
        service.createOperation(operationRequest);
        return ResponseEntity.ok("Entidad creada con éxito");
    }

    @RequestMapping(value = "movimientos", method = RequestMethod.PATCH)
    @ResponseBody
    public ResponseEntity<?> updateOperation(@RequestBody OperationVO operationRequest) throws IOException {
        service.updateOperation(operationRequest);
        return ResponseEntity.ok("Entidad creada con éxito");
    }

    @DeleteMapping("/movimientos/{id}")
    public ResponseEntity<String> cancelOperation(@PathVariable Integer id) throws IOException {
        service.cancelOperation(id);
        return ResponseEntity.ok("Entidad eliminada con éxito");
    }
}