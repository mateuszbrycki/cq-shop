package com.cqshop.simulator.scenario.reservationlocking.actor;

/**
 * Created by Mateusz Brycki on 2019-02-09.
 */
public class RegularCustomerActor extends AbstractCustomerActor {

    public RegularCustomerActor(long id) {
        super(id);
    }

    @Override
    public int performNextAction() {
        return 0;
    }

}
