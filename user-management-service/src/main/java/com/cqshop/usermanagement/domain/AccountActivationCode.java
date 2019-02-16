package com.cqshop.usermanagement.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Mateusz Brycki on 08/12/2018.
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
@EqualsAndHashCode
public class AccountActivationCode {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long activationCodeId;

    @OneToOne
    @JoinColumn(name = "userId")
    private User user;

    @NotNull
    private String activationCode;

}
