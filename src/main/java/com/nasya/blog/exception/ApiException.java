package com.nasya.blog.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiException extends RuntimeException {

    private final HttpStatus status;

    public ApiException(String message, HttpStatus httpStatus) {
      super(message);
      this.status = httpStatus;
    }
}
