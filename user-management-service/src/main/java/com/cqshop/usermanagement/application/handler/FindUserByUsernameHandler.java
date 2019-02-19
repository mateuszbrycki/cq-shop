package com.cqshop.usermanagement.application.handler;

import com.cqshop.cqrs.common.handler.query.QueryHandler;
import com.cqshop.cqrs.common.handler.query.QueryHandlerAnnotation;
import com.cqshop.usermanagement.application.query.FindUserByUsername;
import com.cqshop.usermanagement.domain.User;
import com.cqshop.usermanagement.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by Mateusz Brycki on 2019-02-19.
 */
@Slf4j
@RequiredArgsConstructor
@Component
@QueryHandlerAnnotation
public class FindUserByUsernameHandler implements QueryHandler<FindUserByUsername, Optional<User>> {

    private final UserRepository userRepository;

    @Override
    public Optional<User> handle(FindUserByUsername findUserByUsername) {

        return userRepository.findByUsername(findUserByUsername.getUsername());
    }
}
