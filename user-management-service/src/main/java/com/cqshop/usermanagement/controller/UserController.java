package com.cqshop.usermanagement.controller;

import com.cqshop.cqrs.common.gate.Gate;
import com.cqshop.usermanagement.application.command.RegisterAccountCommand;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Mateusz Brycki on 01/10/2018.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final Gate gate;

    public UserController(Gate gate) {
        this.gate = gate;
    }

    @PostMapping
    public HttpStatus createUser(@RequestBody RegisterAccountCommand command) {
        Object result = gate.dispatch(command);

        return HttpStatus.CREATED;
    }
}
