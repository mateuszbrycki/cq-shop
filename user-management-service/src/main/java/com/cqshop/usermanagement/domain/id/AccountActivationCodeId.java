package com.cqshop.usermanagement.domain.id;

import lombok.*;

import java.io.Serializable;

/**
 * Created by Mateusz Brycki on 08/12/2018.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class AccountActivationCodeId implements Serializable {

    private Long userId;

    private String activationCode;
}
