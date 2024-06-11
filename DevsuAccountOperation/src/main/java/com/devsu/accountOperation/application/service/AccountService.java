package com.devsu.accountOperation.application.service;

import com.devsu.accountOperation.domain.entity.Account;
import com.devsu.accountOperation.domain.repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    IAccountRepository repository;

    public List<com.devsu.accountOperation.infraestructure.vo.Account> getAllAccounts(){
        List<com.devsu.accountOperation.infraestructure.vo.Account> accounts= new ArrayList<>();
        List<Account> accountsDB= repository.findAll();
        accountsDB.forEach(i -> {
            com.devsu.accountOperation.infraestructure.vo.Account account=new com.devsu.accountOperation.infraestructure.vo.Account();
            account.setAccountNumber(i.getAccountNumber());
            account.setAccountType(i.getAccountType());
            account.setState(i.isState());
            account.setClientId(i.getClientId());
            account.setInitialBalance(i.getInitialBalance());
            accounts.add(account);
        });
        return accounts;
    }

    public com.devsu.accountOperation.infraestructure.vo.Account getAccountById(Integer id){
        Optional<Account> accountDB=repository.findById(id);
        if (accountDB.isEmpty()) throw new EntityNotFoundException("Error obteniendo información");
        com.devsu.accountOperation.infraestructure.vo.Account account=new com.devsu.accountOperation.infraestructure.vo.Account();
        account.setAccountNumber(accountDB.get().getAccountNumber());
        account.setAccountType(accountDB.get().getAccountType());
        account.setState(accountDB.get().isState());
        account.setClientId(accountDB.get().getClientId());
        account.setInitialBalance(accountDB.get().getInitialBalance());
        return account;
    }

    public void deleteAccount(Integer id){
        Optional<Account> accountDB=repository.findById(id);
        if (accountDB.isEmpty()) throw new EntityNotFoundException("Error obteniendo información");
        accountDB.get().setState(false);
        repository.save(accountDB.get());
    }

    public void createAccount(com.devsu.accountOperation.infraestructure.vo.Account accountRequest){
        Account account= new Account();
        account.setAccountNumber(accountRequest.getAccountNumber());
        account.setAccountType(accountRequest.getAccountType());
        account.setState(accountRequest.isState());
        account.setClientId(accountRequest.getClientId());
        account.setInitialBalance(accountRequest.getInitialBalance());
        repository.save(account);
    }

    public void updateAccount(com.devsu.accountOperation.infraestructure.vo.Account accountRequest){
        Optional<Account> accountDB=repository.findById(accountRequest.getAccountNumber());
        if (accountDB.isEmpty()) throw new EntityNotFoundException("Error obteniendo información");

        Optional.ofNullable(accountRequest.getAccountType())
                .filter(accountType -> !accountType.trim().isEmpty())
                .ifPresent(accountDB.get()::setAccountType);
        Optional.of(accountRequest.getClientId())
                .filter(initialBalance -> initialBalance!=0)
                .ifPresent(accountDB.get()::setClientId);
        Optional.ofNullable(accountRequest.getInitialBalance())
                .filter(initialBalance -> initialBalance.compareTo(BigInteger.ZERO) >= 0)
                .ifPresent(accountDB.get()::setInitialBalance);
        accountDB.get().setState(accountRequest.isState());

        repository.save(accountDB.get());
    }
}