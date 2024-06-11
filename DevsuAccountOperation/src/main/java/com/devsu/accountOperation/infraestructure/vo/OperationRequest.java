package com.devsu.accountOperation.infraestructure.vo;

import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.util.Date;


@Generated
@Getter
@Setter
/**
 * Objeto de mapeo para comunicación API REST
 *
 * @author Olman Ibanez
 */
public class OperationRequest {
    private Date operationDate;
    private String operationType;
    private BigInteger operationValue;
    private BigInteger newBalance;
    private int accountNumber;
}