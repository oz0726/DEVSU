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
        info = @Info(title = "Devsu Account API", version = "v1",
                description = "A project using Spring Boot with Swagger-UI enabled",
                contact = @Contact(url = "https://www.linkedin.com/in/olman-ibanez/", name = "Olman Ibañez")))
public class AccountController {
    @Autowired
    private AccountService service;

    private final ClientREST clientREST;

    public AccountController(ClientREST clientREST) {
        this.clientREST = clientREST;
    }

    @Operation(summary = "Returns a list of the accounts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = AccountVO.class)))),
            @ApiResponse(responseCode = "400", description = "bad request",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "500", description = "internal error",
                    content = @Content(schema = @Schema(implementation = Response.class)))})
    @GetMapping("/cuentas")
    public ResponseEntity<?>getAllAccounts() {
        return ResponseEntity.ok(service.getAllAccounts());
    }

    @Operation(summary = "Returns a specific account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = AccountVO.class))),
            @ApiResponse(responseCode = "400", description = "bad request",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "500", description = "internal error",
                    content = @Content(schema = @Schema(implementation = Response.class)))})
    @GetMapping("/cuentas/{id}")
    public ResponseEntity<?> getAccount(@PathVariable Integer id) throws InfoNotFoundException {
        return ResponseEntity.ok(service.getAccountById(id));
    }

    @Operation(summary = "Create a account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "400", description = "bad request",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "500", description = "internal error",
                    content = @Content(schema = @Schema(implementation = Response.class)))})
    @RequestMapping(value = "cuentas", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> createAccount(@RequestBody AccountVO accountVORequest) throws InfoNotFoundException {
        clientREST.getClient(accountVORequest.getClientId());
        service.createAccount(accountVORequest);
        Response<String> response = new Response<>(HttpStatus.OK.value(), "Entidad creada con éxito");
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Update a account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "400", description = "bad request",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "500", description = "internal error",
                    content = @Content(schema = @Schema(implementation = Response.class)))})
    @RequestMapping(value = "cuentas", method = RequestMethod.PATCH)
    @ResponseBody
    public ResponseEntity<?> updateAccount(@RequestBody AccountVO accountVORequest) throws InfoNotFoundException {
        if(accountVORequest.getClientId()!=0)clientREST.getClient(accountVORequest.getClientId());
        service.updateAccount(accountVORequest);
        Response<String> response = new Response<>(HttpStatus.OK.value(), "Entidad modificada con éxito");
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete a account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "400", description = "bad request",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "500", description = "internal error",
                    content = @Content(schema = @Schema(implementation = Response.class)))})
    @DeleteMapping("/cuentas/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable Integer id) throws InfoNotFoundException {
        service.deleteAccount(id);
        Response<String> response = new Response<>(HttpStatus.OK.value(), "Entidad eliminada con éxito");
        return ResponseEntity.ok(response);
    }
}