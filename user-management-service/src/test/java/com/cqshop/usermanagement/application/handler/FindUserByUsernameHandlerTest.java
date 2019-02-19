package com.cqshop.usermanagement.application.handler;

import com.cqshop.usermanagement.application.query.FindUserByUsername;
import com.cqshop.usermanagement.application.query.ListAllNotArchivedUsers;
import com.cqshop.usermanagement.domain.User;
import com.cqshop.usermanagement.domain.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Created by Mateusz Brycki on 2019-02-19.
 */
@RunWith(MockitoJUnitRunner.class)
public class FindUserByUsernameHandlerTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private FindUserByUsernameHandler handler;

    @Test
    public void shouldReturnEmptyUser_noUserInDB() {
        //given
        when(userRepository.findByUsername("name")).thenReturn(Optional.empty());

        //when
        Optional<User> users = handler.handle(new FindUserByUsername("name"));

        //then
        assertTrue(users.isEmpty());
    }

    @Test
    public void shouldReturnNonUser() {
        //given
        when(userRepository.findByUsername("name")).thenReturn(Optional.of(
                User.builder().username("name").build()
        ));

        //when
        Optional<User> user = handler.handle(new FindUserByUsername("name"));

        //then
        assertTrue(user.isPresent());
        assertEquals("name", user.get().getUsername());
    }
}