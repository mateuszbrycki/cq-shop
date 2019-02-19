package com.cqshop.cqrs.common.gate;

import com.cqshop.cqrs.common.command.ApplicationCommand;
import com.cqshop.cqrs.common.query.ApplicationQuery;

/**
 * Created by Mateusz Brycki on 02/10/2018.
 */
public interface Gate {

    //TODO mbrycki dispatchAsync
    <T> T dispatch(ApplicationCommand command);
    <T> T dispatch(ApplicationQuery command);
}
