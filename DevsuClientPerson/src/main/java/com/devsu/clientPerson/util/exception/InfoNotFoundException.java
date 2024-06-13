package com.devsu.clientPerson.util.exception;
/**
 * Custom Exception for Info Not Found responses
 *
 * @author Olman Ibanez
 */
public class InfoNotFoundException extends RuntimeException  {
    public InfoNotFoundException(String message) {
        super(message);
    }
    public InfoNotFoundException() {
        super();
    }
}