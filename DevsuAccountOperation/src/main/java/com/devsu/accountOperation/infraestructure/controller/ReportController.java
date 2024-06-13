package com.devsu.accountOperation.infraestructure.controller;

import com.devsu.accountOperation.application.service.AccountService;
import com.devsu.accountOperation.application.service.OperationService;
import com.devsu.accountOperation.infraestructure.rest.ClientREST;
import com.devsu.accountOperation.infraestructure.vo.AccountVO;
import com.devsu.accountOperation.infraestructure.vo.ClientVO;
import com.devsu.accountOperation.infraestructure.vo.OperationVO;
import com.devsu.accountOperation.infraestructure.vo.ReportVO;
import com.devsu.accountOperation.util.exception.InfoNotFoundException;
import com.devsu.accountOperation.util.rest.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.servers.Server;

@RestController
@OpenAPIDefinition(servers = { @Server(url = "http://localhost:8081") },
        info = @Info(title = "Devsu Report API", version = "v1",
                description = "A project using Spring Boot with Swagger-UI enabled",
                contact = @Contact(url = "https://www.linkedin.com/in/olman-ibanez/", name = "Olman Ibañez")))
public class ReportController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private OperationService operationService;

    private final ClientREST clientREST;

    public ReportController(ClientREST clientREST) {
        this.clientREST = clientREST;
    }

    @Operation(summary = "Returns a report of the specified client in a date range")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = ReportVO.class))),
            @ApiResponse(responseCode = "400", description = "bad request",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "500", description = "internal error",
                    content = @Content(schema = @Schema(implementation = Response.class)))})
    @GetMapping("/reportes")
    public ResponseEntity<?> getReport(@RequestParam int clientId, @RequestParam String startDate, @RequestParam String endDate) {
        ReportVO report = new ReportVO();

        ClientVO client=clientREST.getClient(clientId);

        List<AccountVO> accounts = new ArrayList<>();
        List<OperationVO> operations= new ArrayList<>();
        DateFormat df = DateFormat. getDateInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date dStartDate;
        Date dEndDate;

        try {
            dStartDate = sdf.parse(startDate);
            dEndDate = sdf.parse(endDate);
        } catch (ParseException e) {
            throw new InfoNotFoundException();
        }

        accounts=accountService.getAllAccountsByClientId(clientId);
        operations=operationService.getOperationsByClientAndDate(clientId, dStartDate, dEndDate);
        report.setStartDate(dStartDate);
        report.setEndDate(dEndDate);
        report.setAccounts(accounts);
        report.setOperations(operations);
        report.setClientName(client.getName());
        return ResponseEntity.ok(report);
    }
}