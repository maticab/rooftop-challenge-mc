package com.rooftopmc.challenge.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class TextanalizerNotFoundException extends RuntimeException {

    private final HttpStatus httpStatus;

    public TextanalizerNotFoundException(HttpStatus httpStatus) {
        super("Text not found");
        this.httpStatus = httpStatus;
    }
}
