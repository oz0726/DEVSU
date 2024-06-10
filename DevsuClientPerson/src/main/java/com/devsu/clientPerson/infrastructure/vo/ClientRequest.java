package com.devsu.clientPerson.infrastructure.vo;

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
public class ClientRequest {
    private String name;
    private String genre;
    private String age;
    private String address;
    private String phoneNumber;
    private int idNumber;
    private String password;
    private boolean state;
}
