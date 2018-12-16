package com.cqshop.gateway.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

/**
 * Created by Mateusz Brycki on 15/12/2018.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RemoveAccountRequest {

    @NotNull
    private String password;
}
