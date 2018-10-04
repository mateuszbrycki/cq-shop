package com.cqshop.usermanagement.application.handler;

import com.cqshop.cqrs.common.handler.CommandHandler;
import com.cqshop.cqrs.common.handler.CommandHandlerAnnotation;
import com.cqshop.usermanagement.application.command.RegisterAccountCommand;
import com.cqshop.usermanagement.domain.User;
import com.cqshop.usermanagement.domain.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Mateusz Brycki on 03/10/2018.
 */
@CommandHandlerAnnotation
public class RegisterAccountCommandHandler implements CommandHandler<RegisterAccountCommand, User> {

    private static final Logger logger = LoggerFactory.getLogger(RegisterAccountCommandHandler.class);

    private final UserService repository;

    public RegisterAccountCommandHandler(UserService repository) {
        this.repository = repository;
    }

    @Override
    public User handle(RegisterAccountCommand registerAccountCommand) {

        User user = User.builder()
                .username(registerAccountCommand.getUsername())
                .password(registerAccountCommand.getPassword())
                .build();

        repository.save(user);

        logger.info("Received registerAccountCommand: " + registerAccountCommand);

        return user;
    }
}
