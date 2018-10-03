package com.cqshop.usermanagement.domain.factory;

import com.cqshop.usermanagement.application.command.RegisterAccountCommand;
import com.cqshop.usermanagement.domain.User;
import org.springframework.stereotype.Component;

/**
 * Created by Mateusz Brycki on 01/10/2018.
 */
@Component
public class UserFactory {

    public User create(RegisterAccountCommand command) {
        return User.builder()
                .username(command.getUsername())
                .username(command.getPassword())
                .status(User.Status.ACTIVATED)
                .build();
    }
}
