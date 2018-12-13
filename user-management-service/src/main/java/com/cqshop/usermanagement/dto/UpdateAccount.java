package com.cqshop.usermanagement.dto;

import lombok.*;

/**
 * Created by Mateusz Brycki on 19/11/2018.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UpdateAccount {
    @NonNull
    private String username;

    @NonNull
    private String password;

}
