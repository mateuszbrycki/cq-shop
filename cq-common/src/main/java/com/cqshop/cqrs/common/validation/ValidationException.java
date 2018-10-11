package com.cqshop.cqrs.common.validation;

/**
 * Created by Mateusz Brycki on 11/10/2018.
 */
public class ValidationException extends RuntimeException {

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
