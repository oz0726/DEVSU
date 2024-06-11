package com.devsu.accountOperation.infraestructure.vo;

import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Generated
@Getter
@Setter
/**
 * Objeto de mapeo para comunicación API REST
 *
 * @author Olman Ibanez
 */
public class OperationVO {
    private int operationId;
    private Date operationDate;
    private String operationType;
    private Long operationValue;
    private Long newBalance;
    private boolean state;
    private int accountNumber;
}