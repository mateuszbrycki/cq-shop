package com.cqshop.usermanagement.domain.event;

import com.cqshop.cqrs.common.command.AbstractApplicationCommand;
import com.cqshop.usermanagement.infrastructure.Event;
import lombok.*;

import java.time.Instant;

/**
 * Created by Mateusz Brycki on 01/10/2018.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCreatedEvent extends Event {

    private Long userId;
}
