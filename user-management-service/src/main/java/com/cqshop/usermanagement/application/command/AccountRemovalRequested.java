package com.cqshop.usermanagement.application.command;

import com.cqshop.cqrs.common.command.AbstractApplicationCommand;
import com.cqshop.cqrs.common.command.ApplicationCommandAnnotation;
import lombok.*;

import java.io.Serializable;

/**
 * Created by Mateusz Brycki on 15/12/2018.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@ApplicationCommandAnnotation
@Builder
public class AccountRemovalRequested extends AbstractApplicationCommand implements Serializable {

    private long userId;
}
