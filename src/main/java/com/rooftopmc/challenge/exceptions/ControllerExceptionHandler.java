package com.rooftopmc.challenge.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorMsg resourceNotFoundException(RuntimeException ex) {
        ErrorMsg errorMsg;
        if (ex instanceof TextanalizerNotFoundException) {
            TextanalizerNotFoundException textanalizerNotFoundException = (TextanalizerNotFoundException) ex;
            errorMsg = new ErrorMsg(textanalizerNotFoundException.getMessage(), textanalizerNotFoundException.getHttpStatus().value());
        } else {
            errorMsg = new ErrorMsg(ex.toString(), HttpStatus.UNPROCESSABLE_ENTITY.value());
        }
        return errorMsg;
    }
}
