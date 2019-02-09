package com.cqshop.gateway.repository;

import com.cqshop.gateway.dto.UserAuthResponse;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;

/**
 * Created by Mateusz Brycki on 16/12/2018.
 */
@FeignClient("user-management-service")
public interface UserManagementRepository {

    @RequestLine("GET /api/user?username={username}")
    ResponseEntity<UserAuthResponse> findByUsername(@Param("username") String username);

}
