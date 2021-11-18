package com.artur.summer.backend.advices;

import com.artur.summer.backend.exception.WrongUsernameOrPassword;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value
            = {WrongUsernameOrPassword.class})
    protected ResponseEntity<Object> handleWrongPassword(
            RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, "",
                new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
    }
}
