package com.devsu.accountOperation.domain.repository;

import com.devsu.accountOperation.domain.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
/**
 * JPA Connection Repository
 *
 * @author Olman Ibanez
 */
public interface IAccountRepository extends JpaRepository<Account, Integer> {
    @Query("SELECT a FROM Account a WHERE a.clientId = :id")
    public List<Account> findByClientId(@Param("id") Integer id);
}