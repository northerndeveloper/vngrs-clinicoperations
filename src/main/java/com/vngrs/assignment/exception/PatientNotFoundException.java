package com.vngrs.assignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author alperkopuz
 * Custom RunTimeException class used an abstraction for the ControllerAdvice.
 * When there is no patient, the exception will be thrown
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class PatientNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 2708737588376685362L;

    public PatientNotFoundException(String message) {
        super(message);
    }

}
