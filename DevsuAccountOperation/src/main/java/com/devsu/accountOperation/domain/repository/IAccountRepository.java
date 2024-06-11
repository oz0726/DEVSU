package com.devsu.accountOperation.domain.repository;

import com.devsu.accountOperation.domain.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAccountRepository extends JpaRepository<Account, Integer> {

}