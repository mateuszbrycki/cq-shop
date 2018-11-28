package com.cqshop.usermanagement.application.handler;

import com.cqshop.cqrs.common.handler.CommandHandler;
import com.cqshop.cqrs.common.handler.CommandHandlerAnnotation;
import com.cqshop.usermanagement.application.command.AccountDetailsProvided;
import com.cqshop.usermanagement.domain.User;
import com.cqshop.usermanagement.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Mateusz Brycki on 03/10/2018.
 */
@RequiredArgsConstructor
@CommandHandlerAnnotation
public class AccountDetailsProvidedHandler implements CommandHandler<AccountDetailsProvided, User> {

    private static final Logger logger = LoggerFactory.getLogger(AccountDetailsProvidedHandler.class);

    private final UserService service;

    @Override
    public User handle(AccountDetailsProvided accountDetailsProvided) {

        logger.info("Received accountDetailsProvided: " + accountDetailsProvided);

        String password = accountDetailsProvided.getPassword();
        String username = accountDetailsProvided.getUsername();
        String email = accountDetailsProvided.getEmail();

        User user = User.builder()
                .username(username)
                .password(password)
                .email(email)
                .build();

        service.save(user);

        return user;
    }
}
