package com.devsu.accountOperation.infraestructure.vo;

import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
/**
 * Mapping object for REST API communication
 *
 * @author Olman Ibanez
 */
@Generated
@Getter
@Setter
public class AccountVO {
    private int accountNumber;
    private String accountType;
    private Long initialBalance;
    private boolean state;
    private int clientId;
}
