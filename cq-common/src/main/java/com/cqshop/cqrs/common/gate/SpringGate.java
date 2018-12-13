package com.cqshop.cqrs.common.gate;

import com.cqshop.cqrs.common.command.AbstractApplicationCommand;
import com.cqshop.cqrs.common.command.ApplicationCommand;
import com.cqshop.cqrs.common.handler.CommandHandler;
import com.cqshop.cqrs.common.handler.CommandHandlerProvider;
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

    private final ApplicationCommandPublisher commandPublisher;


    @Override
    public <T> T dispatch(AbstractApplicationCommand command) {

        CommandHandler<ApplicationCommand, T> commandHandler = commandHandlerProvider.findHandler(command);

        //TODO mbrycki implement asynchronous command
        T result = commandHandler.handle(command);

        commandPublisher.publish(command);
        return result;
    }
}
