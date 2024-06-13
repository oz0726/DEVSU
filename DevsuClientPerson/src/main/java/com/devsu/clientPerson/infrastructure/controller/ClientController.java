package com.devsu.clientPerson.infrastructure.controller;
import com.devsu.clientPerson.application.service.ClientService;
import com.devsu.clientPerson.infrastructure.vo.ClientVO;
import com.devsu.clientPerson.util.exception.InfoNotFoundException;
import com.devsu.clientPerson.util.rest.Response;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@OpenAPIDefinition(servers = { @Server(url = "http://localhost:8080") },
        info = @Info(title = "Devsu Client API", version = "v1",
                description = "A project using Spring Boot with Swagger-UI enabled",
                contact = @Contact(url = "https://www.linkedin.com/in/olman-ibanez/", name = "Olman Ibañez")))
public class ClientController {
    @Autowired
    private ClientService service;

    @Operation(summary = "Returns a list of the clients")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ClientVO.class)))),
            @ApiResponse(responseCode = "400", description = "bad request",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "500", description = "internal error",
                    content = @Content(schema = @Schema(implementation = Response.class)))})
    @GetMapping("/clientes")
    public ResponseEntity<?> getAllClients() throws IOException {
        return ResponseEntity.ok(service.getAllClients());
    }

    @Operation(summary = "Returns a specific client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = ClientVO.class))),
            @ApiResponse(responseCode = "400", description = "bad request",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "500", description = "internal error",
                    content = @Content(schema = @Schema(implementation = Response.class)))})
    @GetMapping("/clientes/{id}")
    public ResponseEntity<?> getClient(@PathVariable Integer id) throws IOException {
        return ResponseEntity.ok(service.getClientByPersonId(id));
    }

    @Operation(summary = "Create a client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "400", description = "bad request",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "500", description = "internal error",
                    content = @Content(schema = @Schema(implementation = Response.class)))})
    @RequestMapping(value = "clientes", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> createClient(@RequestBody ClientVO clientRequest) throws InfoNotFoundException {
        service.createClient(clientRequest);
        Response<String> response = new Response<>(HttpStatus.CREATED.value(), "Entidad creada con éxito");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Update a client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "400", description = "bad request",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "500", description = "internal error",
                    content = @Content(schema = @Schema(implementation = Response.class)))})
    @RequestMapping(value = "clientes", method = RequestMethod.PATCH)
    @ResponseBody
    public ResponseEntity<?> updateClient(@RequestBody ClientVO clientRequest) throws InfoNotFoundException {
        service.updateClient(clientRequest);
        Response<String> response = new Response<>(HttpStatus.OK.value(), "Entidad modificada con éxito");
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete a client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "400", description = "bad request",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "500", description = "internal error",
                    content = @Content(schema = @Schema(implementation = Response.class)))})
    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable Integer id) throws IOException {
        service.deleteClient(id);
        Response<String> response = new Response<>(HttpStatus.NO_CONTENT.value(), "Entidad eliminada con éxito");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }
}