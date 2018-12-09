package com.cqshop.usermanagement.domain.exception;

/**
 * Created by Mateusz Brycki on 08/12/2018.
 */

public class UserNotFoundException extends Exception {
    public UserNotFoundException() {
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
