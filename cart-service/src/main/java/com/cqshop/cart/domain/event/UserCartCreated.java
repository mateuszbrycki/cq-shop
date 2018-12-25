package com.cqshop.cart.domain.event;

import com.cqshop.kafka.event.Event;
import lombok.*;


/**
 * Created by Mateusz Brycki on 25/12/2018.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCartCreated extends Event {

    @NonNull
    private Long userId;

    @NonNull
    private Long cartId;
}
