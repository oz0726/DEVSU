package com.devsu.accountOperation.util.exception;

public class InfoNotFoundException extends RuntimeException  {
    public InfoNotFoundException(String message) {
        super(message);
    }
    public InfoNotFoundException() {
        super();
    }
}