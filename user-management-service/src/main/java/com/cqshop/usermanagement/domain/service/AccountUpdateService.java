package com.cqshop.usermanagement.domain.service;

import com.cqshop.cqrs.common.validation.ValidationException;
import com.cqshop.usermanagement.domain.User;
import com.cqshop.usermanagement.domain.event.UserAccountUpdated;
import com.cqshop.usermanagement.domain.repository.UserRepository;
import com.cqshop.usermanagement.infrastructure.EventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    public boolean updateAccount(User newUserData) {

        Optional<User> user = userRepository.findById(newUserData.getID());

        if (!user.isPresent()) {
            throw new ValidationException("User with " + newUserData.getID() + " does not exist.");
        }

        User existingUser = user.get();
        existingUser.setPassword(newUserData.getPassword());
        existingUser.setUsername(newUserData.getUsername());
        userRepository.save(existingUser);


        this.eventPublisher.publish(
                UserAccountUpdated.builder()
                        .userId(existingUser.getID())
                        .email(existingUser.getEmail())
                        .username(existingUser.getUsername())
                        .build()
        );

        log.info(String.format("User %s (%d) updated successfully.", existingUser.getUsername(), existingUser.getID()));

        return true;

    }
}
