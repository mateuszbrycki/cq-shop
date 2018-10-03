package com.cqshop.usermanagement.controller;

import com.cqshop.cqrs.common.dispatcher.CommandDispatcher;
import com.cqshop.usermanagement.application.command.RegisterAccountCommand;
import com.cqshop.usermanagement.domain.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Mateusz Brycki on 01/10/2018.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final CommandDispatcher dispatcher;

    public UserController(CommandDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    @PostMapping
    public User createUser(RegisterAccountCommand command) {
        dispatcher.dispatch(command);

        return null;
    }
}
