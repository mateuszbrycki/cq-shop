package com.cqshop.cqrs.common.command;

import java.time.Instant;
import java.time.LocalDateTime;

/**
 * Created by Mateusz Brycki on 02/10/2018.
 */
public abstract class AbstractApplicationCommandWithTimestamp implements ApplicationCommand {

    private final long timestamp;

    public AbstractApplicationCommandWithTimestamp() {
        this.timestamp = Instant.now().toEpochMilli();
    }

    public long getTimestamp() {
        return timestamp;
    }
}
