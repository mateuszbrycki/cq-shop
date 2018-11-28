package com.cqshop.notification.infrastructure;

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
    protected Long timestamp = Instant.now().toEpochMilli();
}
