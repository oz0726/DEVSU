package com.devsu.accountOperation.domain.entity;
import javax.persistence.*;

import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
/**
 * JPA Entity Mapping Object
 *
 * @author Olman Ibanez
 */
@Entity
@Table(name = "ACCOUNT")
@Generated
public @Getter @Setter class Account {
    @Id
    @Column(name = "ACCOUNT_NUMBER", nullable = false)
    private int accountNumber;
    @Column(name = "ACCOUNT_TYPE")
    private String accountType;
    @Column(name = "INITIAL_BALANCE")
    private Long initialBalance;
    @Column(name = "STATE")
    private boolean state;
    @Column(name = "CLIENT_ID")
    private int clientId;
}