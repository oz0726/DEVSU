package com.devsu.accountOperation.infraestructure.vo;

import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
/**
 * Mapping object for REST API communication
 *
 * @author Olman Ibanez
 */
@Generated
@Getter
@Setter
public class OperationVO {
    private int operationId;
    private Date operationDate;
    private String operationType;
    private Long operationValue;
    private Long newBalance;
    private boolean state;
    private int accountNumber;
}