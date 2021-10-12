package com.keita.filingcabinet.exception;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@NoArgsConstructor
@ResponseStatus(code = HttpStatus.CONFLICT)
public class PatientAlreadyExistException extends Exception {

    public PatientAlreadyExistException(String message) {
        super(message);
    }
}
