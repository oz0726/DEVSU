package com.devsu.accountOperation.util.exception;

import com.devsu.accountOperation.util.rest.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * Custom Global exception handler
 *
 * @author Olman Ibanez
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    Logger log = LogManager.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler(InfoNotFoundException.class)
    public ResponseEntity<Response<String>> handleInfoNotFoundException(InfoNotFoundException ex) {
        Response<String> response = new Response<>(HttpStatus.BAD_REQUEST.value(), "Error Obteniendo Datos");
        log.error("Error Obteniendo Datos {}", HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(InvalidOperationException.class)
    public ResponseEntity<Response<String>> handleInvalidOperationException(InfoNotFoundException ex) {
        Response<String> response = new Response<>(HttpStatus.OK.value(), "El movimiento no se puede realizar");
        log.error("El movimiento no se puede realizar {}", HttpStatus.OK.value());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response<String>> handleGenericException(Exception ex) {
        Response<String> response = new Response<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Ocurrió un error inesperado: "+ex.getMessage());
        log.error("Ocurrió un error inesperado: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
