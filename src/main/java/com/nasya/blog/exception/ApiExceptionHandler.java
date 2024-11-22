package com.nasya.blog.exception;


import com.nasya.blog.model.response.ResponseError;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ResponseError> handler(ApiException apiException) {
        return ResponseEntity.status(apiException.getStatus())
                .body(ResponseError.builder()
                        .status(apiException.getStatus())
                        .message(apiException.getMessage())
                        .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseError> handler(MethodArgumentNotValidException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ResponseError.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .message(e.getMessage())
                        .build());
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ResponseError> handler(SQLIntegrityConstraintViolationException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseError.builder()
                .status(HttpStatus.BAD_REQUEST).message(e.getMessage())
                .build());
    }
}
