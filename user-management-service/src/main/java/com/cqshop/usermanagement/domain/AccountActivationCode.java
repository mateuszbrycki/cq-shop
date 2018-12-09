package com.cqshop.usermanagement.domain;

import com.cqshop.usermanagement.domain.id.AccountActivationCodeId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.validation.constraints.NotNull;

/**
 * Created by Mateusz Brycki on 08/12/2018.
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
@IdClass(AccountActivationCodeId.class)
public class AccountActivationCode {

    @Id
    @NotNull
    private Long userId;

    @Id
    @NotNull
    private String activationCode;

}
