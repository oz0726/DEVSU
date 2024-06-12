package com.devsu.accountOperation.application.service;

import com.devsu.accountOperation.domain.repository.IAccountRepository;
import com.devsu.accountOperation.infraestructure.vo.AccountVO;
import com.devsu.accountOperation.domain.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    IAccountRepository repository;

    public List<AccountVO> getAllAccounts(){
        List<AccountVO> accountVOS = new ArrayList<>();
        List<Account> accountsDB= repository.findAll();
        accountsDB.forEach(i -> {
            AccountVO accountVO =new AccountVO();
            accountVO.setAccountNumber(i.getAccountNumber());
            accountVO.setAccountType(i.getAccountType());
            accountVO.setState(i.isState());
            accountVO.setClientId(i.getClientId());
            accountVO.setInitialBalance(i.getInitialBalance());
            accountVOS.add(accountVO);
        });
        return accountVOS;
    }

    public AccountVO getAccountById(Integer id){
        Optional<Account> accountDB=repository.findById(id);
        if (accountDB.isEmpty()) throw new EntityNotFoundException("Error obteniendo información");
        AccountVO accountVO =new AccountVO();
        accountVO.setAccountNumber(accountDB.get().getAccountNumber());
        accountVO.setAccountType(accountDB.get().getAccountType());
        accountVO.setState(accountDB.get().isState());
        accountVO.setClientId(accountDB.get().getClientId());
        accountVO.setInitialBalance(accountDB.get().getInitialBalance());
        return accountVO;
    }

    public void deleteAccount(Integer id){
        Optional<Account> accountDB=repository.findById(id);
        if (accountDB.isEmpty()) throw new EntityNotFoundException("Error obteniendo información");
        accountDB.get().setState(false);
        repository.save(accountDB.get());
    }

    public void createAccount(AccountVO accountVORequest){
        Account account= new Account();
        account.setAccountNumber(accountVORequest.getAccountNumber());
        account.setAccountType(accountVORequest.getAccountType());
        account.setState(true);
        account.setClientId(accountVORequest.getClientId());
        account.setInitialBalance(accountVORequest.getInitialBalance());
        repository.save(account);
    }

    public void updateAccount(AccountVO accountVORequest){
        Optional<Account> accountDB=repository.findById(accountVORequest.getAccountNumber());
        if (accountDB.isEmpty()) throw new EntityNotFoundException("Error obteniendo información");

        Optional.ofNullable(accountVORequest.getAccountType())
                .filter(accountType -> !accountType.trim().isEmpty())
                .ifPresent(accountDB.get()::setAccountType);
        Optional.of(accountVORequest.getClientId())
                .filter(clientId -> clientId !=0)
                .ifPresent(accountDB.get()::setClientId);
        Optional.ofNullable(accountVORequest.getInitialBalance())
                .filter(initialBalance -> initialBalance>= 0)
                .ifPresent(accountDB.get()::setInitialBalance);
        accountDB.get().setState(true);

        repository.save(accountDB.get());
    }

    public List<AccountVO> getAllAccountsByClientId(Integer id){
        List<AccountVO> accountVOS = new ArrayList<>();
        List<Account> accountsDB= repository.findByClientId(id);
        accountsDB.forEach(i -> {
            AccountVO accountVO =new AccountVO();
            accountVO.setAccountNumber(i.getAccountNumber());
            accountVO.setAccountType(i.getAccountType());
            accountVO.setState(i.isState());
            accountVO.setClientId(i.getClientId());
            accountVO.setInitialBalance(i.getInitialBalance());
            accountVOS.add(accountVO);
        });
        return accountVOS;
    }
}