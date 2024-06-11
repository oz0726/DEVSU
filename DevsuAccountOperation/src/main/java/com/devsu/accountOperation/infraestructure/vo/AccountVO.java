package com.devsu.accountOperation.infraestructure.vo;

import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

@Generated
@Getter
@Setter
/**
 * Objeto de mapeo para comunicación API REST
 *
 * @author Olman Ibanez
 */
public class AccountVO {
    private int accountNumber;
    private String accountType;
    private Long initialBalance;
    private boolean state;
    private int clientId;
}
