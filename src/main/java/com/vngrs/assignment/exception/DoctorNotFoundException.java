package com.vngrs.assignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author alperkopuz
 * Custom RunTimeException class used an abstraction for the ControllerAdvice.
 * When there is no doctor, the exception will be thrown
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class DoctorNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 5852196294910062295L;

    public DoctorNotFoundException(String message) {
        super(message);
    }

}
