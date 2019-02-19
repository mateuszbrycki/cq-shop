package com.cqshop.cqrs.common.gate;

import com.cqshop.cqrs.common.command.ApplicationCommand;
import com.cqshop.cqrs.common.handler.command.CommandHandler;
import com.cqshop.cqrs.common.handler.AbstractHandlerProvider;
import com.cqshop.cqrs.common.handler.command.CommandHandlerProvider;
import com.cqshop.cqrs.common.handler.query.QueryHandler;
import com.cqshop.cqrs.common.handler.query.QueryHandlerProvider;
import com.cqshop.cqrs.common.query.ApplicationQuery;
import com.cqshop.kafka.ApplicationCommandPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Created by Mateusz Brycki on 02/10/2018.
*/
@RequiredArgsConstructor
@Component
public class SpringGate implements Gate {

    private final CommandHandlerProvider commandHandlerProvider;
    private final QueryHandlerProvider queryHandlerProvider;

    private final ApplicationCommandPublisher commandPublisher;


    @Override
    public <T> T dispatch(ApplicationCommand command) {

        CommandHandler<ApplicationCommand, T> commandHandler = commandHandlerProvider.findHandler(command);

        //TODO mbrycki implement asynchronous command
        T result = commandHandler.handle(command);

        commandPublisher.publish(command);
        return result;
    }

    @Override
    public <T> T dispatch(ApplicationQuery query) {

        QueryHandler<ApplicationQuery, T> queryHandler = queryHandlerProvider.findHandler(query);

        T result = queryHandler.handle(query);

        commandPublisher.publish(query);
        return result;
    }
}
