package com.cqshop.usermanagement.controller;

import com.cqshop.cqrs.common.gate.Gate;
import com.cqshop.usermanagement.application.command.AccountDetailsProvided;
import com.cqshop.usermanagement.application.command.ActivationLinkClicked;
import com.cqshop.usermanagement.dto.RegisterAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
        Object result = gate.dispatch(
                AccountDetailsProvided.builder()
                .email(registerAccount.getEmail())
                .password(registerAccount.getPassword())
                .username(registerAccount.getUsername())
                .build()
        );

        return HttpStatus.CREATED;
    }


    @GetMapping("/activation/{userId}/{activationCode}")
    public HttpStatus activateAccountByClickingLink(@PathVariable("userId") Long userId,
                                                    @PathVariable("activationCode") String activationCode) {

        //TODO mbrycki check result
        Object result = gate.dispatch(
                ActivationLinkClicked.builder()
                .userId(userId)
                .activationCode(activationCode)
                .build()
        );

        return HttpStatus.OK;
    }
}
