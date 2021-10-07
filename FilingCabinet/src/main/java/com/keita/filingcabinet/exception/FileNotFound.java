package com.keita.filingcabinet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class FileNotFound extends Exception {

    public FileNotFound(String message) {
        super(message);
    }
}
