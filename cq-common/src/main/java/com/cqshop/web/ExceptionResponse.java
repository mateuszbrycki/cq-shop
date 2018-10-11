package com.cqshop.web;

/**
 * Created by Mateusz Brycki on 11/10/2018.
 */
public class ExceptionResponse {

    public final String message;


    public ExceptionResponse(Exception exception) {
        this.message = exception.getMessage();
    }
}
