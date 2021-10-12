package com.keita.filingcabinet.exception;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@NoArgsConstructor
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class FolderNotFoundException extends Exception {

    public FolderNotFoundException(String message) {
        super(message);
    }
}
