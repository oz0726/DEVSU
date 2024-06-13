package com.devsu.accountOperation.application.service;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.devsu.accountOperation.application.service.OperationService;
import com.devsu.accountOperation.domain.entity.Account;
import com.devsu.accountOperation.domain.entity.Operation;
import com.devsu.accountOperation.domain.repository.IAccountRepository;
import com.devsu.accountOperation.domain.repository.IOperationRepository;
import com.devsu.accountOperation.infraestructure.vo.OperationVO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class OperationServiceTest {

    @Mock
    private IOperationRepository operationRepository;
    @Mock
    private IAccountRepository accountRepository;

    @InjectMocks
    private OperationService operationService;

    private List<Operation> mockOperationList;

    @Before
    public void setup() {
        mockOperationList = new ArrayList<>();
        Operation operation1 = new Operation();
        Account account1= new Account();
        operation1.setOperationId(1);
        operation1.setOperationDate(new Date(1623481200000L));
        operation1.setOperationValue(200L);
        operation1.setOperationType("test");
        operation1.setState(true);
        operation1.setNewBalance(200L);
        account1.setAccountNumber(1);
        account1.setState(true);
        account1.setAccountType("test");
        account1.setClientId(1);
        account1.setInitialBalance(200L);
        operation1.setAccount(account1);
        mockOperationList.add(operation1);
        when(operationRepository.findAll()).thenReturn(mockOperationList);
        when(operationRepository.findById(1)).thenReturn(Optional.of(operation1));
        when(operationRepository.findByClientAndDate(1,new Date(1623481200000L),new Date(1623481200000L))).thenReturn(mockOperationList);
        when(accountRepository.findById(1)).thenReturn(Optional.of(account1));
    }

    @Test
    public void testGetAllClients() {
        List<OperationVO> result = operationService.getAllOperations();
        assertEquals(mockOperationList.size(), result.size());
    }

    @Test
    public void testGetClientById() {
        OperationVO result = operationService.getOperationById(1);
        assertEquals(1, result.getOperationId());
    }

    @Test
    public void testGetOperationsByClientAndDate() {
        List<OperationVO> result = operationService.getOperationsByClientAndDate(1,new Date(1623481200000L),new Date(1623481200000L));
        assertEquals(mockOperationList.size(), result.size());
    }

    @Test
    public void testCreateClient() {
        OperationVO given= new OperationVO();
        given.setOperationId(1);
        given.setOperationDate(new Date(1623481200000L));
        given.setOperationValue(200L);
        given.setOperationType("test");
        given.setState(true);
        given.setNewBalance(200L);
        given.setAccountNumber(1);
        operationService.createOperation(given);
        ArgumentCaptor<Operation> operationCaptor = ArgumentCaptor.forClass(Operation.class);
        verify(operationRepository).save(operationCaptor.capture());
        assertEquals(given.getOperationValue(), operationCaptor.getValue().getOperationValue());
    }

    @Test
    public void testUpdateClientById() {
        OperationVO given= new OperationVO();
        given.setOperationId(1);
        given.setOperationDate(new Date(1623481200000L));
        given.setOperationValue(200L);
        given.setOperationType("test");
        given.setState(true);
        given.setNewBalance(200L);
        given.setAccountNumber(1);
        operationService.updateOperation(given);
        ArgumentCaptor<Operation> operationCaptor = ArgumentCaptor.forClass(Operation.class);
        verify(operationRepository,times(3)).save(operationCaptor.capture());
        assertEquals(given.getOperationValue(), operationCaptor.getValue().getOperationValue());
    }

    @Test
    public void testDeleteClientById() {
        operationService.cancelOperation(1);
        ArgumentCaptor<Operation> operationCaptor = ArgumentCaptor.forClass(Operation.class);
        verify(operationRepository,times(2)).save(operationCaptor.capture());
        assertFalse(operationCaptor.getValue().isState());
    }
}