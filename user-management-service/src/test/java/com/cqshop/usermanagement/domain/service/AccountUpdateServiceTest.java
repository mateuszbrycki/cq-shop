package com.cqshop.usermanagement.domain.service;

import com.cqshop.usermanagement.domain.User;
import com.cqshop.usermanagement.domain.event.UserAccountUpdated;
import com.cqshop.usermanagement.domain.exception.UserNotFoundException;
import com.cqshop.usermanagement.domain.repository.UserRepository;
import com.cqshop.usermanagement.infrastructure.EventPublisher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Mateusz Brycki on 2019-02-10.
 */
@RunWith(MockitoJUnitRunner.class)
public class AccountUpdateServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private EventPublisher eventPublisher;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AccountUpdateService accountUpdateService;

    @Test(expected = UserNotFoundException.class)
    public void shouldThrowUserNotFoundExceptionWhenUserDoesNotExist() throws UserNotFoundException {
        //given
        User user = User.builder()
                .userId(1l)
                .email("email")
                .password("password")
                .username("username")
                .build();

        when(userRepository.findById(1l)).thenReturn(Optional.empty());

        //when
        accountUpdateService.updateAccount(user);
    }

    @Test
    public void shouldUpdateAccountAndPublishEvent() throws UserNotFoundException {
        //given
        User user = User.builder()
                .userId(1l)
                .email("email")
                .password("password-new")
                .username("username-new")
                .build();

        when(userRepository.findById(1l)).thenReturn(Optional.of(User.builder()
                .userId(1l)
                .email("email-old")
                .password("password-old")
                .username("username-old")
                .build()));

        //when
        boolean result = accountUpdateService.updateAccount(user);

        //then
        assertTrue(result);
        verify(eventPublisher).publish(UserAccountUpdated.builder()
                .userId(user.getUserId())
                .email("email-old")
                .username("username-new")
                .build());
    }


}