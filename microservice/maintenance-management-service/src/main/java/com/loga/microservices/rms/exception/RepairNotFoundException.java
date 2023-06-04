package com.loga.microservices.rms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RepairNotFoundException extends RuntimeException {
    public RepairNotFoundException(String message) {
        super(message);
    }
}
