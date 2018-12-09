package com.cqshop.usermanagement.application.handler;

import com.cqshop.cqrs.common.handler.CommandHandler;
import com.cqshop.cqrs.common.handler.CommandHandlerAnnotation;
import com.cqshop.usermanagement.application.command.AccountDetailsProvided;
import com.cqshop.usermanagement.domain.User;
import com.cqshop.usermanagement.domain.service.UserRegistrationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Mateusz Brycki on 03/10/2018.
 */
@Slf4j
@RequiredArgsConstructor
@CommandHandlerAnnotation
public class AccountDetailsProvidedHandler implements CommandHandler<AccountDetailsProvided, User> {

    private final UserRegistrationService userRegistrationService;

    @Override
    public User handle(AccountDetailsProvided accountDetailsProvided) {

        log.info("Received accountDetailsProvided: " + accountDetailsProvided);

        String password = accountDetailsProvided.getPassword();
        String username = accountDetailsProvided.getUsername();
        String email = accountDetailsProvided.getEmail();

        User user = User.builder()
                .username(username)
                .password(password)
                .email(email)
                .build();

        userRegistrationService.registerUser(user);

        return user;
    }
}
