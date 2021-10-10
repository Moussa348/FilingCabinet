package com.keita.filingcabinet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FolderNotFoundException extends Exception {

    public FolderNotFoundException(String message) {
        super(message);
    }
}
