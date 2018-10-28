package com.cqshop.usermanagement.application.handler;

import com.cqshop.cqrs.common.handler.CommandHandler;
import com.cqshop.cqrs.common.handler.CommandHandlerAnnotation;
import com.cqshop.cqrs.common.validation.ValidationException;
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

    private final UserService service;

    public RegisterAccountCommandHandler(UserService service) {
        this.service = service;
    }

    @Override
    public User handle(RegisterAccountCommand registerAccountCommand) {

        logger.info("Received registerAccountCommand: " + registerAccountCommand);

        String password = registerAccountCommand.getPassword();
        String username = registerAccountCommand.getUsername();
        String email = registerAccountCommand.getEmail();

        //TODO mbrycki find better way of validation
        if (isNullOrEmpty(password)
                || isNullOrEmpty(username)
                || isNullOrEmpty(email)) {
            throw new ValidationException("Password, username and email cannot be empty");
        }

        if (isUnique(email)) {
            throw new ValidationException("User with " + email + " already exists.");
        }

        User user = User.builder()
                .username(username)
                .password(password)
                .email(email)
                .build();

        service.save(user);

        return user;
    }

    private boolean isUnique(String email) {
        return service.findByEmail(email) != null;
    }

    private boolean isNullOrEmpty(String value) {
        return value == null || value.isEmpty();
    }
}
