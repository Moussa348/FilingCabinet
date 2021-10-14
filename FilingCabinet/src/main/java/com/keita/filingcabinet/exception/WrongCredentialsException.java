package com.keita.filingcabinet.exception;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@NoArgsConstructor
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class WrongCredentialsException extends Exception {

    public WrongCredentialsException(String message) {
        super(message);
    }
}
