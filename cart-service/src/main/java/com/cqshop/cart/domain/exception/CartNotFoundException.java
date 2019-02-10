package com.cqshop.cart.domain.exception;

/**
 * Created by Mateusz Brycki on 2019-02-10.
 */
public class CartNotFoundException extends Exception {
    public CartNotFoundException() {
    }

    public CartNotFoundException(String message) {
        super(message);
    }
}
