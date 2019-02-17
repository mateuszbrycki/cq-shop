package com.cqshop.simulator.service.dto;

import lombok.*;

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
}
