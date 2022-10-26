package com.example.relexproject2.controllers;

import com.example.relexproject2.exceptions.FileLoadException;
import com.example.relexproject2.exceptions.OperationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(ApiExceptionHandler.class);

    @ExceptionHandler(value = FileLoadException.class)
    public ResponseEntity<Object> handleFileLoadException(FileLoadException e) {
        return handle(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = OperationException.class)
    public ResponseEntity<Object> handleOperationFailedException(OperationException e) {
        return handle(e, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException e) {
        return handle(e, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<Object> handleOtherException(RuntimeException e) {
        return handle(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<Object> handle(RuntimeException e, HttpStatus status) {
        logger.error(e.getMessage(), e);
        Map<String, String> body = new LinkedHashMap<>();
        body.put("message", e.getMessage());
        return new ResponseEntity<>(body, status);
    }
}
