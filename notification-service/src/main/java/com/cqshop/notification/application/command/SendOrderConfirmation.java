package com.cqshop.notification.application.command;

import com.cqshop.cqrs.common.command.AbstractApplicationCommand;
import com.cqshop.cqrs.common.command.ApplicationCommandAnnotation;
import lombok.*;

import java.io.Serializable;

/**
 * Created by Mateusz Brycki on 28/12/2018.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@ApplicationCommandAnnotation
@Builder
public class SendOrderConfirmation extends AbstractApplicationCommand implements Serializable {

    @NonNull
    private long userId;

    @NonNull
    private long orderId;
}

