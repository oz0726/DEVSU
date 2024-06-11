package com.devsu.accountOperation.infraestructure.vo;

import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Generated
@Getter
@Setter
/**
 * Objeto de mapeo para comunicación API REST
 *
 * @author Olman Ibanez
 */
public class Account {
    private int accountNumber;
    private String accountType;
    private BigInteger initialBalance;
    private boolean state;
    private int clientId;
}
