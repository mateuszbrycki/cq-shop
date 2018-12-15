package com.cqshop.usermanagement.domain.service;

import com.cqshop.usermanagement.domain.User;
import com.cqshop.usermanagement.domain.event.UserAccountRemoved;
import com.cqshop.usermanagement.domain.exception.UserNotFoundException;
import com.cqshop.usermanagement.domain.repository.UserRepository;
import com.cqshop.usermanagement.infrastructure.EventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by Mateusz Brycki on 15/12/2018.
 */
@Slf4j
@Repository
@Transactional
@RequiredArgsConstructor
public class UserDeletionService {

    private final UserRepository userRepository;
    private final EventPublisher eventPublisher;

    public boolean delete(long userId) throws UserNotFoundException {

        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(userId));

/*        boolean arePasswordsEqual = user.getPassword().equals(password);
        if (!arePasswordsEqual) {
            return false;
        }*/

        user.setStatus(User.Status.ARCHIVED);
        userRepository.save(user);

        eventPublisher.publish(UserAccountRemoved.builder()
            .userId(userId)
            .build());

        return true;
    }
}
