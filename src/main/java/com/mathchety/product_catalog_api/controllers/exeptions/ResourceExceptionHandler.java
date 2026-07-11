package com.mathchety.product_catalog_api.controllers.exeptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    // Captura erros de validação de dados enviados pelo usuário (Ex: Deixar o nome vazio ou colocar um preço negativo)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;

        String errorMessage = e.getBindingResult().getFieldError().getDefaultMessage();

        StandardError err = new StandardError(
                Instant.now(),
                status.value(),
                "Validation Error",
                errorMessage,
                request.getRequestURI()
        );

        return ResponseEntity.status(status).body(err);
    }

    // Captura o erro quando tentamos buscar algo no banco de dados por um ID que simplesmente não existe
    @ExceptionHandler(java.util.NoSuchElementException.class)
    public ResponseEntity<StandardError> entityNotFound(java.util.NoSuchElementException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;

        StandardError err = new StandardError(
                Instant.now(),
                status.value(),
                "Resource not found",
                "ID not found",
                request.getRequestURI()
        );

        return ResponseEntity.status(status).body(err);
    }

    // Captura erros de regras de negócio que nós criamos no código (Ex: Tentar cadastrar uma categoria com nome que já existe)
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<StandardError> businessLogic(RuntimeException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        StandardError err = new StandardError(
                Instant.now(),
                status.value(),
                "Business Rule Error",
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(err);
    }

    // Captura erros de segurança do banco de dados (Ex: Tentar apagar uma categoria que ainda possui produtos vinculados a ela)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> databaseViolation(DataIntegrityViolationException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        StandardError err = new StandardError(
                Instant.now(),
                status.value(),
                "Database Integrity Error",
                "You cannot perform this action because it violates database integrity constraints.",
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(err);
    }

    // A nossa "rede de segurança": Captura qualquer outro erro inesperado e desconhecido para o sistema não quebrar feio pro usuário
    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardError> globalError(Exception e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        StandardError err = new StandardError(
                Instant.now(),
                status.value(),
                "Internal Server Error",
                "An unexpected error occurred on the server.",
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(err);
    }
}