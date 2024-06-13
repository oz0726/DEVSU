package com.devsu.accountOperation.infraestructure.controller;

import com.devsu.accountOperation.application.service.OperationService;
import com.devsu.accountOperation.infraestructure.vo.AccountVO;
import com.devsu.accountOperation.infraestructure.vo.OperationVO;
import com.devsu.accountOperation.util.exception.InfoNotFoundException;
import com.devsu.accountOperation.util.exception.InvalidOperationException;
import com.devsu.accountOperation.util.rest.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.servers.Server;

@RestController
@OpenAPIDefinition(servers = { @Server(url = "http://localhost:8081") },
        info = @Info(title = "Devsu Operation API", version = "v1",
                description = "A project using Spring Boot with Swagger-UI enabled",
                contact = @Contact(url = "https://www.linkedin.com/in/olman-ibanez/", name = "Olman Ibañez")))
public class OperationController {
    @Autowired
    private OperationService service;

    @Operation(summary = "Returns a list of the operations")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = OperationVO.class)))),
            @ApiResponse(responseCode = "400", description = "bad request",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "500", description = "internal error",
                    content = @Content(schema = @Schema(implementation = Response.class)))})
    @GetMapping("/movimientos")
    public ResponseEntity<?> getAllOperations() {
        return ResponseEntity.ok(service.getAllOperations());
    }

    @Operation(summary = "Returns a specific operations")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = OperationVO.class))),
            @ApiResponse(responseCode = "400", description = "bad request",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "500", description = "internal error",
                    content = @Content(schema = @Schema(implementation = Response.class)))})
    @GetMapping("/movimientos/{id}")
    public ResponseEntity<?> getOperationById(@PathVariable Integer id) throws InfoNotFoundException {
        return ResponseEntity.ok(service.getOperationById(id));
    }

    @Operation(summary = "Create a operation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "400", description = "bad request",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "500", description = "internal error",
                    content = @Content(schema = @Schema(implementation = Response.class)))})
    @RequestMapping(value = "movimientos", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> createOperation(@RequestBody OperationVO operationRequest) throws InvalidOperationException, InfoNotFoundException {
        service.createOperation(operationRequest);
        Response<String> response = new Response<>(HttpStatus.OK.value(), "Entidad creada con éxito");
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Update a operation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "400", description = "bad request",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "500", description = "internal error",
                    content = @Content(schema = @Schema(implementation = Response.class)))})
    @RequestMapping(value = "movimientos", method = RequestMethod.PATCH)
    @ResponseBody
    public ResponseEntity<?> updateOperation(@RequestBody OperationVO operationRequest) throws InfoNotFoundException {
        service.updateOperation(operationRequest);
        Response<String> response = new Response<>(HttpStatus.OK.value(), "Entidad modificada con éxito");
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete a operation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "400", description = "bad request",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "500", description = "internal error",
                    content = @Content(schema = @Schema(implementation = Response.class)))})
    @DeleteMapping("/movimientos/{id}")
    public ResponseEntity<?> cancelOperation(@PathVariable Integer id) throws InfoNotFoundException {
        service.cancelOperation(id);
        Response<String> response = new Response<>(HttpStatus.OK.value(), "Entidad eliminada con éxito");
        return ResponseEntity.ok(response);
    }
}