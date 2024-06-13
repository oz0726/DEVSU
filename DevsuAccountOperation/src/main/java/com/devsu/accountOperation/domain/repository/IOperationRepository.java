package com.devsu.accountOperation.domain.repository;

import com.devsu.accountOperation.domain.entity.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Date;
import java.util.List;
/**
 * JPA Connection Repository
 *
 * @author Olman Ibanez
 */
public interface IOperationRepository extends JpaRepository<Operation, Integer> {
    @Query("SELECT o FROM Operation o WHERE o.account.clientId = :id AND o.operationDate BETWEEN :startDate AND :endDate")
    public List<Operation> findByClientAndDate(@Param("id") Integer id, @Param("startDate") Date startDate, @Param("endDate") Date endDate);
}