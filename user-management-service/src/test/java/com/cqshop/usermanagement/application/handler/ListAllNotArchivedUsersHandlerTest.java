package com.cqshop.usermanagement.application.handler;

import com.cqshop.usermanagement.application.query.ListAllNotArchivedUsers;
import com.cqshop.usermanagement.domain.UserRole;
import com.cqshop.usermanagement.domain.repository.UserRepository;
import com.cqshop.usermanagement.dto.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Created by Mateusz Brycki on 2019-02-19.
 */
@RunWith(MockitoJUnitRunner.class)
public class ListAllNotArchivedUsersHandlerTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ListAllNotArchivedUsersHandler handler;

    @Test
    public void shouldReturnEmptyListOfUsers_noUsersInDB() {
        //given
        when(userRepository.findAll()).thenReturn(Collections.emptyList());

        //when
        List<User> users = handler.handle(new ListAllNotArchivedUsers());

        //then
        assertTrue(users.isEmpty());
    }

    @Test
    public void shouldReturnEmptyListOfUsers_noUsersActiveInDB() {

        //given
        when(userRepository.findAll()).thenReturn(Arrays.asList(
                com.cqshop.usermanagement.domain.User.builder().status(com.cqshop.usermanagement.domain.User.Status.ARCHIVED).build(),
                com.cqshop.usermanagement.domain.User.builder().status(com.cqshop.usermanagement.domain.User.Status.ARCHIVED).build()
        ));

        //when
        List<User> users = handler.handle(new ListAllNotArchivedUsers());

        //then
        assertTrue(users.isEmpty());
    }

    @Test
    public void shouldReturnOneActiveUser() {

        //given
        when(userRepository.findAll()).thenReturn(Arrays.asList(
                com.cqshop.usermanagement.domain.User.builder().status(com.cqshop.usermanagement.domain.User.Status.ARCHIVED).build(),
                com.cqshop.usermanagement.domain.User.builder()
                        .role(UserRole.builder().build())
                        .status(com.cqshop.usermanagement.domain.User.Status.ACTIVATED)
                        .build()
        ));

        //when
        List<User> users = handler.handle(new ListAllNotArchivedUsers());

        //then
        assertEquals(1, users.size());
    }

    @Test
    public void shouldReturnTwoActiveUser() {

        //given
        when(userRepository.findAll()).thenReturn(Arrays.asList(
                com.cqshop.usermanagement.domain.User.builder().status(com.cqshop.usermanagement.domain.User.Status.ARCHIVED).build(),
                com.cqshop.usermanagement.domain.User.builder()
                        .role(UserRole.builder().build())
                        .status(com.cqshop.usermanagement.domain.User.Status.ACTIVATED)
                        .build(),
                com.cqshop.usermanagement.domain.User.builder()
                        .role(UserRole.builder().build())
                        .status(com.cqshop.usermanagement.domain.User.Status.ACTIVATED)
                        .build()
        ));

        //when
        List<User> users = handler.handle(new ListAllNotArchivedUsers());

        //then
        assertEquals(2, users.size());
    }
}