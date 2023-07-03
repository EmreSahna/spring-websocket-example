package com.emresahna.websocketexample.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GeneralExceptionHandler {
    @ExceptionHandler(UserCredentialsException.class)
    public ResponseEntity<ExceptionResponse> handle(UserCredentialsException exception) {
        var response = ExceptionResponse.builder()
                .message(exception.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .timestamp(System.currentTimeMillis())
                .build();

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(UsernameAlreadyTakenException.class)
    public ResponseEntity<ExceptionResponse> handle(UsernameAlreadyTakenException exception) {
        var response = ExceptionResponse.builder()
                .message(exception.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .timestamp(System.currentTimeMillis())
                .build();

        return ResponseEntity.badRequest().body(response);
    }
}