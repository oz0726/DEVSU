package com.devsu.accountOperation.domain.entity;
import javax.persistence.*;

import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "OPERATION")
@Generated
public @Getter @Setter class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OPERATION_ID", nullable = false)
    private int operationId;
    @Column(name = "OPERATION_DATE")
    private Date operationDate;
    @Column(name = "OPERATION_TYPE")
    private String operationType;
    @Column(name = "OPERATION_VALUE")
    private Long operationValue;
    @Column(name = "NEW_BALANCE")
    private Long newBalance;
    @Column(name = "STATE")
    private boolean state;
    @JoinColumn(name = "ACCOUNT", referencedColumnName = "ACCOUNT_NUMBER", nullable = false)
    @ManyToOne(optional = false, cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    private Account account;
}