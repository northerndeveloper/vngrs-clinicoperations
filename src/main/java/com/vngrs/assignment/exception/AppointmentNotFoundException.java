package com.vngrs.assignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author alperkopuz
 * Custom RunTimeException class used an abstraction for the ControllerAdvice.
 * When there is no appointment, the exception will be thrown
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class AppointmentNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 6080939116998543974L;

    public AppointmentNotFoundException(String message) {
        super(message);
    }
}
