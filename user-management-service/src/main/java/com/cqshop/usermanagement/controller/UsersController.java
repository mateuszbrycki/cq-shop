package com.cqshop.usermanagement.controller;

import com.cqshop.usermanagement.domain.repository.UserRepository;
import com.cqshop.usermanagement.dto.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Mateusz Brycki on 2019-02-16.
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UsersController {

    private final UserRepository userRepository;

    @GetMapping
    public List<User> listUsers() {
        return userRepository.findAll()
                .stream()
                .filter(user -> !user.getStatus().equals(com.cqshop.usermanagement.domain.User.Status.ARCHIVED))
                .map(User::of)
                .collect(Collectors.toList());
    }
}
