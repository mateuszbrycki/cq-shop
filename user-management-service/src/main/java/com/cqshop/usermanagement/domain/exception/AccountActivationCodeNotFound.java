package com.cqshop.usermanagement.domain.exception;

/**
 * Created by Mateusz Brycki on 08/12/2018.
 */
public class AccountActivationCodeNotFound extends Exception {
    public AccountActivationCodeNotFound() {
    }

    public AccountActivationCodeNotFound(String message) {
        super(message);
    }
}
