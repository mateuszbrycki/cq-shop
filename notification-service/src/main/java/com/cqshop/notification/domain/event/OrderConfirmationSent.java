package com.cqshop.notification.domain.event;

import com.cqshop.kafka.event.Event;
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
public class OrderConfirmationSent extends Event {

    @NonNull
    private long userId;

    @NonNull
    private long orderId;
}
