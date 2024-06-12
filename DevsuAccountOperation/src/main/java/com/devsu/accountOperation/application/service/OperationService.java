package com.devsu.accountOperation.application.service;

import com.devsu.accountOperation.domain.entity.Account;
import com.devsu.accountOperation.domain.repository.IAccountRepository;
import com.devsu.accountOperation.domain.repository.IOperationRepository;
import com.devsu.accountOperation.infraestructure.vo.OperationVO;
import com.devsu.accountOperation.domain.entity.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OperationService {
    @Autowired
    IOperationRepository operationRepository;
    @Autowired
    IAccountRepository accountRepository;

    public List<OperationVO> getAllOperations(){
        List<OperationVO> operations= new ArrayList<>();
        List<Operation> operationsDB= operationRepository.findAll();
        operationsDB.forEach(i -> {
            OperationVO operation=new OperationVO();
            operation.setOperationId(i.getOperationId());
            operation.setOperationDate(i.getOperationDate());
            operation.setOperationType(i.getOperationType());
            operation.setOperationValue(i.getOperationValue());
            operation.setNewBalance(i.getNewBalance());
            operation.setState(i.isState());
            operation.setAccountNumber(i.getAccount().getAccountNumber());
            operations.add(operation);
        });
        return operations;
    }

    public OperationVO getOperationById(Integer id){
        Optional<Operation> operationDB=operationRepository.findById(id);
        if (operationDB.isEmpty()) throw new EntityNotFoundException("Error obteniendo información");
        OperationVO operation=new OperationVO();
        operation.setOperationId(operationDB.get().getOperationId());
        operation.setOperationDate(operationDB.get().getOperationDate());
        operation.setOperationType(operationDB.get().getOperationType());
        operation.setOperationValue(operationDB.get().getOperationValue());
        operation.setAccountNumber(operationDB.get().getAccount().getAccountNumber());
        operation.setNewBalance(operationDB.get().getNewBalance());
        operation.setState(operationDB.get().isState());
        return operation;
    }

    public void createOperation(OperationVO operationRequest){
        Operation operation= new Operation();
        Optional<Account> account= accountRepository.findById(operationRequest.getAccountNumber());
        if (account.isEmpty()) throw new EntityNotFoundException("Error obteniendo información");

        if(account.get().getInitialBalance() + operationRequest.getOperationValue()>=0){
            operation.setOperationDate(operationRequest.getOperationDate());
            operation.setOperationType(operationRequest.getOperationType());
            operation.setOperationValue(operationRequest.getOperationValue());
            operation.setState(true);
            operation.setNewBalance(account.get().getInitialBalance() + operationRequest.getOperationValue());
            account.get().setInitialBalance(account.get().getInitialBalance() + operationRequest.getOperationValue());
            accountRepository.save(account.get());
            operation.setAccount(account.get());
            operationRepository.save(operation);
        } else throw new EntityNotFoundException("Saldo no disponible");
    }

    public void cancelOperation(Integer id){
        Optional<Operation> operationDB=operationRepository.findById(id);
        if (operationDB.isEmpty() || !operationDB.get().isState()) throw new EntityNotFoundException("Error obteniendo información");
        Operation rollback = new Operation();
        rollback.setOperationDate(new Date());
        rollback.setOperationType("Rollback "+operationDB.get().getOperationId());
        rollback.setOperationValue(-operationDB.get().getOperationValue());
        rollback.setState(true);
        rollback.setNewBalance(operationDB.get().getAccount().getInitialBalance() + rollback.getOperationValue());
        operationDB.get().getAccount().setInitialBalance(operationDB.get().getAccount().getInitialBalance() + rollback.getOperationValue());
        operationDB.get().setState(false);
        accountRepository.save(operationDB.get().getAccount());
        rollback.setAccount(operationDB.get().getAccount());
        operationRepository.save(rollback);
        operationRepository.save(operationDB.get());
    }

    public void updateOperation(OperationVO operationRequest){
        cancelOperation(operationRequest.getOperationId());
        createOperation(operationRequest);
    }

    public List<OperationVO> getOperationsByClientAndDate(int clientId, Date startDate, Date endDate){
        List<OperationVO> operations= new ArrayList<>();
        List<Operation> operationsDB= operationRepository.findByClientAndDate(clientId, startDate, endDate);
        operationsDB.forEach(i -> {
            OperationVO operation=new OperationVO();
            operation.setOperationId(i.getOperationId());
            operation.setOperationDate(i.getOperationDate());
            operation.setOperationType(i.getOperationType());
            operation.setOperationValue(i.getOperationValue());
            operation.setNewBalance(i.getNewBalance());
            operation.setState(i.isState());
            operation.setAccountNumber(i.getAccount().getAccountNumber());
            operations.add(operation);
        });
        return operations;
    }
}