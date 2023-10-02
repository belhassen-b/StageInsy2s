package com.example.tpecommerce.error.record;

import org.springframework.http.HttpStatus;


public interface ExceptionWithErrorResponse {
    String getMessage();
    String getCode();
    HttpStatus getHttpStatus();
    int getStatus();
    String getUrl();
}
