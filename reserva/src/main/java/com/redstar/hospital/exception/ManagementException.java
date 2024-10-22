package com.redstar.hospital.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.xml.bind.JAXBException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ManagementException {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> manejarRecursoNoEncontradoException(NotFoundException ex) {
        Map<String, Object> detallesError = new HashMap<>();
        detallesError.put("timestamp", LocalDateTime.now());
        detallesError.put("mensaje", ex.getMessage());
        return new ResponseEntity<>(detallesError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(JAXBException.class)
    public ResponseEntity<String> manejarJAXBException(JAXBException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al procesar XML: " + ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> manejarExcepcionGlobal(Exception ex) {
        Map<String, Object> detallesError = new HashMap<>();
        detallesError.put("timestamp", LocalDateTime.now());
        detallesError.put("mensaje", "Error interno del servidor");
        return new ResponseEntity<>(detallesError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
