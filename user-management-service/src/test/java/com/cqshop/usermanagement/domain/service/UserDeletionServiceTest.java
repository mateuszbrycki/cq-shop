package com.cqshop.usermanagement.domain.service;

import com.cqshop.usermanagement.domain.User;
import com.cqshop.usermanagement.domain.event.UserAccountRemoved;
import com.cqshop.usermanagement.domain.exception.UserNotFoundException;
import com.cqshop.usermanagement.domain.repository.UserRepository;
import com.cqshop.usermanagement.infrastructure.EventPublisher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Mateusz Brycki on 2019-02-10.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserDeletionServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private EventPublisher eventPublisher;

    @InjectMocks
    UserDeletionService userDeletionService;

    @Test(expected = UserNotFoundException.class)
    public void shouldThrowUserNotFoundExceptionWhenUserIsNotPresent() throws UserNotFoundException {
        //then
        userDeletionService.delete(99999l);
    }

    @Test
    public void shouldDeleteUserAndPublishEvent() throws UserNotFoundException {
        //given
        User user = User.builder()
                .userId(1l)
                .username("username")
                .password("password")
                .email("email")
                .build();

        when(userRepository.findById(1l)).thenReturn(Optional.of(user));

        //when
        Boolean result = userDeletionService.delete(1l);

        //then
        assertTrue(result);

        verify(eventPublisher).publish(UserAccountRemoved.builder()
                .userId(user.getUserId())
                .build());

    }


}