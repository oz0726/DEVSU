package com.devsu.accountOperation.domain.repository;

import com.devsu.accountOperation.domain.entity.Account;
import com.devsu.accountOperation.domain.entity.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOperationRepository extends JpaRepository<Operation, Integer> {

}