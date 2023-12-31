package com.example.tpecommerce.error.exception;


import com.example.tpecommerce.error.record.ExceptionWithErrorResponse;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.io.Serial;


@Getter
@ToString
public class UsernameExistException extends RuntimeException implements ExceptionWithErrorResponse {

    @Serial
    private static final long serialVersionUID = -6734474587964597922L;
    private final String message;
    private final String code;
    private final HttpStatus httpStatus;
    private final int status;
    private final String url;

    public UsernameExistException(String url) {
        this.message = "Username already exist";
        this.code = "login_exist";
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.status = httpStatus.value();
        this.url = url;
    }

}
