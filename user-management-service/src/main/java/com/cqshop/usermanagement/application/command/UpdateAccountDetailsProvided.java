package com.cqshop.usermanagement.application.command;

import com.cqshop.cqrs.common.command.AbstractApplicationCommand;
import com.cqshop.cqrs.common.command.ApplicationCommandAnnotation;
import lombok.*;

import javax.validation.constraints.NotNull;
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
@Builder
public class UpdateAccountDetailsProvided extends AbstractApplicationCommand implements Serializable {

    @NonNull
    private String username;

    @NonNull
    private String password;

    @NotNull
    private Long userId;

}
