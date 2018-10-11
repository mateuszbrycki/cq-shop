package com.cqshop.web;

import com.cqshop.cqrs.common.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Mateusz Brycki on 11/10/2018.
 */
@ControllerAdvice
public class GlobalExceptionHandler {


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public @ResponseBody
    ExceptionResponse
    validationException(HttpServletRequest req, Exception e) {
        return new ExceptionResponse(e);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
    public @ResponseBody
    ExceptionResponse
    runtimeException(HttpServletRequest req, Exception e) {
        return new ExceptionResponse(e);
    }
}
