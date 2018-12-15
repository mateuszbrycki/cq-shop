package com.cqshop.usermanagement.domain.event;

import com.cqshop.usermanagement.infrastructure.Event;
import lombok.*;

/**
 * Created by Mateusz Brycki on 15/12/2018.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAccountRemoved extends Event {

    @NonNull
    private Long userId;

}
