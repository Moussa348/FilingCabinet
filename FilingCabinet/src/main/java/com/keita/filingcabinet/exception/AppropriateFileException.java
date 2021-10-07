package com.keita.filingcabinet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE)
public class AppropriateFileException extends Exception {

    public AppropriateFileException(String message) {
        super(message);
    }
}
