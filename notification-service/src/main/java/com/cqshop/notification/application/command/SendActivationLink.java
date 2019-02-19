package com.cqshop.notification.application.command;

import com.cqshop.cqrs.common.command.AbstractApplicationCommandWithTimestamp;
import com.cqshop.cqrs.common.command.ApplicationCommandAnnotation;
import lombok.*;

import java.io.Serializable;

/**
 * Created by Mateusz Brycki on 24/11/2018.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@ApplicationCommandAnnotation
@Builder
public class SendActivationLink extends AbstractApplicationCommandWithTimestamp implements Serializable {

    @NonNull
    private String username;

    @NonNull
    private String email;
}
