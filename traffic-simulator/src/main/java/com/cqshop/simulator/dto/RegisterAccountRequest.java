package com.cqshop.simulator.dto;

import lombok.*;

/**
 * Created by Mateusz Brycki on 19/11/2018.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class RegisterAccountRequest {
    @NonNull
    private String username;

    @NonNull
    private String password;

    @NonNull
    private String email;

}
