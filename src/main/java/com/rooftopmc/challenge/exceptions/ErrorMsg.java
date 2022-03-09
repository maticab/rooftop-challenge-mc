package com.rooftopmc.challenge.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
@Setter
public class ErrorMsg {

    boolean error = true;

    final String message;

    final int code;
}
