package com.cqshop.cqrs.common.query;

import java.time.Instant;

/**
 * Created by Mateusz Brycki on 02/10/2018.
 */
public abstract class AbstractApplicationQueryWithTimestamp implements ApplicationQuery {

    private final long timestamp;

    public AbstractApplicationQueryWithTimestamp() {
        this.timestamp = Instant.now().toEpochMilli();
    }

    public long getTimestamp() {
        return timestamp;
    }
}
