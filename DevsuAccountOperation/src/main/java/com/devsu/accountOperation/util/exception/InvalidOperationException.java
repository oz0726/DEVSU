package com.devsu.accountOperation.util.exception;

public class InvalidOperationException extends RuntimeException   {
    public InvalidOperationException(String message) {
        super(message);
    }
    public InvalidOperationException() {
        super();
    }
}