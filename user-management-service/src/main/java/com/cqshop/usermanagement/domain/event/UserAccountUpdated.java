package com.cqshop.usermanagement.domain.event;

import com.cqshop.kafka.event.Event;
import lombok.*;

import javax.validation.constraints.NotNull;

/**
 * Created by Mateusz Brycki on 13/12/2018.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class UserAccountUpdated extends Event {
    @NonNull
    private Long userId;

    @NotNull
    private String username;

    @NotNull
    private String email;
}
