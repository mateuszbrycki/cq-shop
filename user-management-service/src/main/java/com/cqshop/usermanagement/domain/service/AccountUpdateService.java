package com.cqshop.usermanagement.domain.service;

import com.cqshop.cqrs.common.validation.ValidationException;
import com.cqshop.usermanagement.domain.User;
import com.cqshop.usermanagement.domain.event.UserAccountUpdated;
import com.cqshop.usermanagement.domain.exception.UserNotFoundException;
import com.cqshop.usermanagement.domain.repository.UserRepository;
import com.cqshop.usermanagement.infrastructure.EventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * Created by Mateusz Brycki on 13/12/2018.
 */
@Slf4j
@Repository
@Transactional
@RequiredArgsConstructor
public class AccountUpdateService {
    private final UserRepository userRepository;
    private final EventPublisher eventPublisher;

    private final PasswordEncoder passwordEncoder;


    public boolean updateAccount(User newUserData) throws UserNotFoundException {

        User user = userRepository.findById(newUserData.getUserId()).orElseThrow(
                () -> new UserNotFoundException(newUserData.getUserId()));

        user.setPassword(passwordEncoder.encode(newUserData.getPassword()));
        user.setUsername(newUserData.getUsername());
        userRepository.save(user);


        this.eventPublisher.publish(
                UserAccountUpdated.builder()
                        .userId(user.getUserId())
                        .email(user.getEmail())
                        .username(user.getUsername())
                        .build()
        );

        log.info(String.format("User %s (%d) updated successfully.", user.getUsername(), user.getUserId()));

        return true;

    }
}
