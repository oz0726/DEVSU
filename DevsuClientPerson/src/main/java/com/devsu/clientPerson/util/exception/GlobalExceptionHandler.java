package com.devsu.clientPerson.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.devsu.clientPerson.util.rest.Response;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InfoNotFoundException.class)
    public ResponseEntity<Response<String>> handleInfoNotFoundException(InfoNotFoundException ex) {
        Response<String> response = new Response<>(HttpStatus.BAD_REQUEST.value(), "Error Obteniendo Datos");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response<String>> handleGenericException(Exception ex) {
        Response<String> response = new Response<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Ocurrió un error inesperado: "+ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
