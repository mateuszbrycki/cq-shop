package com.cqshop.usermanagement.controller;

import com.cqshop.cqrs.common.command.AbstractApplicationCommand;
import com.cqshop.cqrs.common.command.ApplicationCommand;
import com.cqshop.cqrs.common.gate.Gate;
import com.cqshop.usermanagement.application.command.AccountDetailsProvided;
import com.cqshop.usermanagement.dto.RegisterAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Mateusz Brycki on 01/10/2018.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final Gate gate;

    @PostMapping
    public HttpStatus createUser(@RequestBody RegisterAccount registerAccount) {
        Object result = gate.dispatch(buildRegisterAccountCommand(registerAccount));

        return HttpStatus.CREATED;
    }

    private AbstractApplicationCommand buildRegisterAccountCommand(RegisterAccount registerAccount) {
        return AccountDetailsProvided.builder()
                .email(registerAccount.getEmail())
                .password(registerAccount.getPassword())
                .username(registerAccount.getUsername())
                .build();
    }
}
