package com.cqshop.gateway.web;

import com.cqshop.gateway.dto.RegisterAccountRequest;
import com.cqshop.gateway.dto.UpdateAccountRequest;
import com.cqshop.gateway.repository.UserManagementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Mateusz Brycki on 16/12/2018.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserManagementController {

    private final UserManagementRepository userManagementRepository;

    @PostMapping
    public HttpStatus createUser(@RequestBody RegisterAccountRequest registerAccount) {
        return userManagementRepository.createAccount(registerAccount);
    }

    @PutMapping("/{userId}")
    public HttpStatus updateUser(@PathVariable("userId") Long userId, @RequestBody UpdateAccountRequest updateAccount) {
        return userManagementRepository.updateAccount(updateAccount, userId);
    }

    @DeleteMapping("/{userId}")
    public HttpStatus removeUser(@PathVariable("userId") Long userId) {
        return userManagementRepository.removeAccount(userId);
    }

    @GetMapping("/{userId}/activation/{activationCode}")
    public HttpStatus activateAccount(@PathVariable("userId") Long userId, @PathVariable("activationCode") String activationCode) {
        return userManagementRepository.activateAccount(userId, activationCode);
    }
}
