package com.cqshop.usermanagement.controller;

import com.cqshop.cqrs.common.gate.Gate;
import com.cqshop.usermanagement.application.command.ActivationLinkClicked;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user/activation")
public class AccountActivationController {

    private final Gate gate;

    @GetMapping("/{userId}/{activationCode}")
    public HttpStatus activateAccountByClickingLink(@PathVariable("userId") Long userId,
                                                    @PathVariable("activationCode") String activationCode) {

        Boolean result = gate.dispatch(
                ActivationLinkClicked.builder()
                        .userId(userId)
                        .activationCode(activationCode)
                        .build()
        );

        if (result) {
            return HttpStatus.OK;
        } else {
            return HttpStatus.BAD_REQUEST;
        }

    }
}
