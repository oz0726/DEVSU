package com.devsu.clientPerson.infrastructure.vo;

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
public class ClientVO {
    private String name;
    private String genre;
    private String age;
    private String address;
    private String phoneNumber;
    private String password;
    private int idNumber;
    private boolean state;
}
