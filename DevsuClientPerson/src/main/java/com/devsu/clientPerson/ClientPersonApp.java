package com.devsu.clientPerson;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * Clase base del app SpringBoot
 *
 * @author Olman Ibanez
 */
@SpringBootApplication
public class ClientPersonApp{
    public static void main(String[] args) {
        SpringApplication.run(ClientPersonApp.class, args);
    }
}