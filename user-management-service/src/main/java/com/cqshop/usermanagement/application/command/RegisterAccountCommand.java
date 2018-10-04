package com.cqshop.usermanagement.application.command;

import com.cqshop.cqrs.common.command.AbstractApplicationCommand;
import com.cqshop.cqrs.common.command.ApplicationCommandAnnotation;
import lombok.*;

import java.io.Serializable;

/**
 * Created by Mateusz Brycki on 01/10/2018.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@ApplicationCommandAnnotation
public class RegisterAccountCommand extends AbstractApplicationCommand implements Serializable {

    private String username;
    private String password;
}