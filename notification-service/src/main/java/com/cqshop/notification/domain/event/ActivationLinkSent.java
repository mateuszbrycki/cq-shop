package com.cqshop.notification.domain.event;

import com.cqshop.notification.infrastructure.Event;
import lombok.*;

import javax.validation.constraints.NotNull;

/**
 * Created by Mateusz Brycki on 24/11/2018.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActivationLinkSent extends Event {

    @NotNull
    private String username;

    @NotNull
    private String email;
}
