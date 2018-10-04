package com.cqshop.usermanagement.domain.service;

import com.cqshop.usermanagement.domain.User;
import com.cqshop.usermanagement.domain.event.UserCreatedEvent;
import com.cqshop.usermanagement.domain.repository.UserRepository;
import com.cqshop.usermanagement.infrastructure.EventPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by Mateusz Brycki on 01/10/2018.
 */
@Repository
@Transactional
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final EventPublisher eventPublisher;

    public UserService(UserRepository userRepository, EventPublisher eventPublisher) {
        this.userRepository = userRepository;
        this.eventPublisher = eventPublisher;
    }

    public User save(User user) {

        user = this.userRepository.save(user);

        this.eventPublisher.publish(
                UserCreatedEvent.builder()
                .userId(user.getID())
                .build()
        );

        logger.info(String.format("User %s (%d) registered successfully.", user.getUsername(), user.getID()));

        return user;
    }

}
