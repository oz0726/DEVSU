package com.devsu.accountOperation.infraestructure.controller;

import com.devsu.accountOperation.application.service.AccountService;
import com.devsu.accountOperation.application.service.OperationService;
import com.devsu.accountOperation.infraestructure.rest.ClientREST;
import com.devsu.accountOperation.infraestructure.vo.AccountVO;
import com.devsu.accountOperation.infraestructure.vo.ClientVO;
import com.devsu.accountOperation.infraestructure.vo.OperationVO;
import com.devsu.accountOperation.infraestructure.vo.ReportVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class ReportController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private OperationService operationService;

    private final ClientREST clientREST;

    public ReportController(ClientREST clientREST) {
        this.clientREST = clientREST;
    }

    @GetMapping("/reportes")
    public ReportVO getReport(@RequestParam int clientId, @RequestParam String startDate, @RequestParam String endDate) {
        ReportVO report = new ReportVO();
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
            throw new RuntimeException("Error al parsear las fechas", e);
        }

        ClientVO client=clientREST.getClient(clientId);
        accounts=accountService.getAllAccountsByClientId(clientId);
        operations=operationService.getOperationsByClientAndDate(clientId, dStartDate, dEndDate);

        report.setStartDate(dStartDate);
        report.setEndDate(dEndDate);
        report.setAccounts(accounts);
        report.setOperations(operations);
        report.setClientName(client.getName());
        return report;
    }
}