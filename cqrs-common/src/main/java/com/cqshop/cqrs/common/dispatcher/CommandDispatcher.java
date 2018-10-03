package com.cqshop.cqrs.common.dispatcher;

import com.cqshop.cqrs.common.command.AbstractApplicationCommand;

/**
 * Created by Mateusz Brycki on 02/10/2018.
 */
public interface CommandDispatcher {

    void dispatch(AbstractApplicationCommand command);
}
