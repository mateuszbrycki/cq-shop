package com.cqshop.usermanagement.domain.repository;

import com.cqshop.usermanagement.domain.User;
import com.cqshop.usermanagement.domain.event.UserCreatedEvent;
import com.cqshop.usermanagement.infrastructure.EventPublisher;
import org.springframework.stereotype.Repository;

/**
 * Created by Mateusz Brycki on 01/10/2018.
 */
@Repository
public class UserEventRepository {

    private final UserRepository userRepository;

    private final EventPublisher eventPublisher;

    public UserEventRepository(UserRepository userRepository, EventPublisher eventPublisher) {
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

        return user;
    }

}
