package com.devsu.accountOperation.util.exception;

/**
 * Custom Exception for invalid operations responses
 *
 * @author Olman Ibanez
 */
public class InvalidOperationException extends RuntimeException   {
    public InvalidOperationException(String message) {
        super(message);
    }
    public InvalidOperationException() {
        super();
    }
}