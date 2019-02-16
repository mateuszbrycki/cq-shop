package com.cqshop.usermanagement.domain.service;

import com.cqshop.cqrs.common.validation.ValidationException;
import com.cqshop.usermanagement.domain.User;
import com.cqshop.usermanagement.domain.UserRole;
import com.cqshop.usermanagement.domain.event.UserAccountCreated;
import com.cqshop.usermanagement.domain.repository.UserRepository;
import com.cqshop.usermanagement.domain.repository.UserRoleRepository;
import com.cqshop.usermanagement.infrastructure.EventPublisher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by Mateusz Brycki on 2019-02-10.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserRegistrationServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserRoleRepository userRoleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private EventPublisher eventPublisher;

    @InjectMocks
    UserRegistrationService userRegistrationService;


    @Test(expected = ValidationException.class)
    public void shouldThrowValidationExceptionWhenUsernameIsEmpty() {
        //given
        User user = User.builder()
                .email("mail")
                .password("password")
                .build();

        //when
        userRegistrationService.registerUser(user);
    }

    @Test(expected = ValidationException.class)
    public void shouldThrowValidationExceptionWhenEmailIsEmpty() {
        //given
        User user = User.builder()
                .username("username")
                .password("password")
                .build();

        //when
        userRegistrationService.registerUser(user);
    }

    @Test(expected = ValidationException.class)
    public void shouldThrowValidationExceptionWhenPasswordIsEmpty() {
        //given
        User user = User.builder()
                .username("username")
                .email("email")
                .build();

        //when
        userRegistrationService.registerUser(user);
    }

    @Test(expected = ValidationException.class)
    public void shouldThrowValidationExceptionWhenEmailIsNotUnique() {

        //given
        User user = User.builder()
                .username("username")
                .email("email")
                .password("password")
                .build();

        when(userRepository.findAllByEmail("email")).thenReturn(Collections.singletonList(User.builder().build()));

        //when
        userRegistrationService.registerUser(user);
    }

    @Test
    public void shouldRegisterUserAndPublishEvent() {
        //given
        User user = User.builder()
                .username("username")
                .email("email")
                .password("password")
                .build();

        when(userRepository.findAllByEmail("email")).thenReturn(Collections.emptyList());
        when(passwordEncoder.encode("password")).thenReturn("passwordEncoded");
        when(userRoleRepository.findByRole(UserRole.DEFAULT_ROLE)).thenReturn(UserRole.builder().build());

        when(userRepository.save(user)).thenReturn(
                User.builder()
                        .username("username")
                        .email("email")
                        .password("passwordEncoded")
                        .userId(1l)
                        .build()
        );

        //when
        User registeredUser = userRegistrationService.registerUser(user);

        //then
        assertEquals("username", registeredUser.getUsername());
        assertEquals("email", registeredUser.getEmail());
        assertEquals("passwordEncoded", registeredUser.getPassword());

        verify(eventPublisher).publish(UserAccountCreated.builder()
                .userId(registeredUser.getUserId())
                .email(registeredUser.getEmail())
                .username(registeredUser.getUsername())
                .build());
    }

}