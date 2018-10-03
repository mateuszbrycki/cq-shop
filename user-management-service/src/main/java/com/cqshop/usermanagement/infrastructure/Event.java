package com.cqshop.usermanagement.infrastructure;

import lombok.*;

import java.time.Instant;

/**
 * Created by Mateusz Brycki on 03/10/2018.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public abstract class Event {
    private Long timestamp = Instant.now().toEpochMilli();
}
