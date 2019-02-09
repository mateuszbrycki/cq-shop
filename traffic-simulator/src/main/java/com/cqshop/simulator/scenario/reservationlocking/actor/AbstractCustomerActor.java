package com.cqshop.simulator.scenario.reservationlocking.actor;

import com.cqshop.simulator.scenario.Actor;

/**
 * Created by Mateusz Brycki on 2019-02-09.
 */
public abstract class AbstractCustomerActor implements Actor {

    protected final long id;

    public AbstractCustomerActor(long id) {
        this.id = id;
    }
}
