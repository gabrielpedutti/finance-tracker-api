package com.pedutti.finance.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handle404() {
        var error = new ErrorResponse(
            LocalDateTime.now(),
            HttpStatus.NOT_FOUND.value(),
            "Recurso não encontrado",
            "O ID informado não existe na nossa base de dados."
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handle400(MethodArgumentNotValidException ex) {
        var errors = ex.getFieldErrors();

        return ResponseEntity.badRequest().body(
            errors.stream()
                .map(e -> new ErrorResponse(
                    LocalDateTime.now(),
                    HttpStatus.BAD_REQUEST.value(),
                    "Requisição inválida",
                    e))
                .toList()
        );
    }

    public record ErrorResponse(
        LocalDateTime timestamp,
        Integer status,
        String error,
        String message) {

        public ErrorResponse(LocalDateTime timestamp, Integer status, String error, FieldError fieldError) {
            this(
                timestamp,
                status,
                error,
                "Campo: " + fieldError.getField() + " - " + fieldError.getDefaultMessage()
            );
        }
    }
}