package com.cqshop.usermanagement.domain.service;

import com.cqshop.cqrs.common.validation.ValidationException;
import com.cqshop.usermanagement.domain.User;
import com.cqshop.usermanagement.domain.event.UserAccountCreated;
import com.cqshop.usermanagement.domain.repository.UserRepository;
import com.cqshop.usermanagement.infrastructure.EventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by Mateusz Brycki on 01/10/2018.
 */
@Slf4j
@Repository
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final EventPublisher eventPublisher;


    public User save(User user) {

        if (isNullOrEmpty(user.getPassword())
                || isNullOrEmpty(user.getUsername())
                || isNullOrEmpty(user.getEmail())) {
            throw new ValidationException("Password, username and email cannot be empty");
        }

        String email = user.getEmail();

        if (isUnique(email)) {
            throw new ValidationException("User with " + email + " already exists.");
        }

        user = this.userRepository.save(user);

        this.eventPublisher.publish(
                UserAccountCreated.builder()
                .userId(user.getID())
                .email(user.getEmail())
                .username(user.getUsername())
                .build()
        );

        log.info(String.format("User %s (%d) registered successfully.", user.getUsername(), user.getID()));

        return user;
    }

    private boolean isUnique(String email) {
        return this.findByEmail(email) != null;
    }


    private boolean isNullOrEmpty(String value) {
        return value == null || value.isEmpty();
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
