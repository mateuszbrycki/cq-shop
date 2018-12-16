package com.cqshop.gateway.repository;

import com.cqshop.gateway.dto.RegisterAccountRequest;
import com.cqshop.gateway.dto.UpdateAccountRequest;
import com.cqshop.gateway.dto.UserAuthResponse;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Created by Mateusz Brycki on 16/12/2018.
 */
@FeignClient("user-management-service")
public interface UserManagementRepository {

    @RequestLine("POST /api/user")
    HttpStatus createAccount(RegisterAccountRequest registerAccount);

    @RequestLine("PUT /api/user/{userId}")
    HttpStatus updateAccount(UpdateAccountRequest updateAccount, @Param("userId") Long userId);

    @RequestLine("DELETE /api/user/{userId}")
    HttpStatus removeAccount(@Param("userId") Long userId);

    @RequestLine("GET /api/user/{userId}/activation/{activationCode}")
    HttpStatus activateAccount(@Param("userId") Long userId, @Param("activationCode") String activationCode);

    @RequestLine("GET /api/user?username={username}")
    ResponseEntity<UserAuthResponse> findByUsername(@Param("username") String username);

}
