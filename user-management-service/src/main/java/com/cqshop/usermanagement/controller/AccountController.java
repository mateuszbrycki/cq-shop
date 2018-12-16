package com.cqshop.usermanagement.controller;

import com.cqshop.cqrs.common.gate.Gate;
import com.cqshop.usermanagement.application.command.AccountDetailsProvided;
import com.cqshop.usermanagement.application.command.AccountRemovalRequested;
import com.cqshop.usermanagement.application.command.UpdateAccountDetailsProvided;
import com.cqshop.usermanagement.domain.User;
import com.cqshop.usermanagement.domain.repository.UserRepository;
import com.cqshop.usermanagement.dto.RegisterAccount;
import com.cqshop.usermanagement.dto.UpdateAccount;
import com.cqshop.usermanagement.dto.UserAuthResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * Created by Mateusz Brycki on 01/10/2018.
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class AccountController {

    private final UserRepository userRepository;
    private final Gate gate;

    @PostMapping
    public HttpStatus createUser(@RequestBody RegisterAccount registerAccount) {
        Object result = gate.dispatch(
                AccountDetailsProvided.builder()
                .email(registerAccount.getEmail())
                .password(registerAccount.getPassword())
                .username(registerAccount.getUsername())
                .build()
        );

        return HttpStatus.CREATED;
    }

    @PutMapping("{userId}")
    public HttpStatus updateAccountData(@PathVariable("userId") Long userId,
                                        @RequestHeader("X-User-Id") Long loggedUser,
                                        @RequestBody UpdateAccount updateAccount) {

        if (isNotAllowed(userId, loggedUser)) {
            log.error("User " + loggedUser + " is not allowed to update the account number " + userId);
            return HttpStatus.FORBIDDEN;
        }

        Boolean result = gate.dispatch(
                UpdateAccountDetailsProvided.builder()
                .userId(getLoggedUserId())
                .password(updateAccount.getPassword())
                .username(updateAccount.getUsername())
                .build()
        );

        return processResult(result);
    }

    @DeleteMapping("/{userId}")
    public HttpStatus removeUserAccount(@PathVariable("userId") Long userId, @RequestHeader("X-User-Id") Long loggedUser) {

        if (isNotAllowed(userId, loggedUser)) {
            log.error("User " + loggedUser + " is not allowed to remove the account number " + userId);
            return HttpStatus.FORBIDDEN;
        }

        Boolean result = gate.dispatch(
                AccountRemovalRequested.builder()
                .userId(userId)
                .build()
        );

        return processResult(result);
    }


    @GetMapping
    public ResponseEntity<UserAuthResponse> findUser(@RequestParam(value = "username") String username) {

        if (username == null || username.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        log.error("Got " + username + " username");

        User user = userRepository.findByUsername(username);

        UserAuthResponse response = UserAuthResponse.builder()
                .password(user.getPassword())
                .username(user.getUsername())
                .status(user.getStatus().toString())
                .userId(user.getUserId())
                .roles(Arrays.asList(user.getRole().getRole()))
                .build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);

    }

    private boolean isNotAllowed(Long userId, Long loggedUser) {
        return !userId.equals(loggedUser);
    }

    private HttpStatus processResult(Boolean result) {
        if (result) {
            return HttpStatus.OK;
        } else {
            return HttpStatus.BAD_REQUEST;
        }
    }

    private Long getLoggedUserId() {
        //TODO mbrycki implement
        return 1l;
    }

}
