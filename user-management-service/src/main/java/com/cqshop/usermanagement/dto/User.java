package com.cqshop.usermanagement.dto;

import com.cqshop.usermanagement.domain.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

/**
 * Created by Mateusz Brycki on 2019-02-16.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class User {

    private Long userId;
    private String username;
    private String status;
    private String role;

    public static User of(com.cqshop.usermanagement.domain.User user) {
        return User.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .status(user.getStatus().toString())
                .role(user.getRole().toString())
                .build();
    }
}
