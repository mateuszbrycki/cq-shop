package com.cqshop.gateway.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by Mateusz Brycki on 16/12/2018.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class UserAuthResponse {

    private Long userId;
    private String username;
    private String password;
    private String status;
    private List<String> roles;

}
