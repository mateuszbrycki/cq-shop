package com.cqshop.usermanagement.domain.exception;

/**
 * Created by Mateusz Brycki on 08/12/2018.
 */

public class UserNotFoundException extends Exception {

    public UserNotFoundException(long userId) {
        super("Cannot find user with userId " + userId);
    }

    public UserNotFoundException(String email) {
        super("Cannot find user with email " + email);
    }

}
