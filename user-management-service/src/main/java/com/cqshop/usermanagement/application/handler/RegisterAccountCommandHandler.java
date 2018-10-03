package com.cqshop.usermanagement.application.handler;

import com.cqshop.cqrs.common.handler.CommandHandler;
import com.cqshop.usermanagement.application.command.RegisterAccountCommand;
import com.cqshop.usermanagement.domain.User;
import com.cqshop.usermanagement.domain.repository.UserEventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by Mateusz Brycki on 03/10/2018.
 */
@Component
public class RegisterAccountCommandHandler implements CommandHandler<RegisterAccountCommand, Object> {

    private static final Logger logger = LoggerFactory.getLogger(RegisterAccountCommandHandler.class);

    private final UserEventRepository repository;

    public RegisterAccountCommandHandler(UserEventRepository repository) {
        this.repository = repository;
    }

    @Override
    public Object handle(RegisterAccountCommand registerAccountCommand) {

        User user = User.builder()
                .username(registerAccountCommand.getUsername())
                .password(registerAccountCommand.getPassword())
                .build();

        repository.save(user);

        logger.error("Received registerAccountCommand: " + registerAccountCommand);
        return null;
    }
}
