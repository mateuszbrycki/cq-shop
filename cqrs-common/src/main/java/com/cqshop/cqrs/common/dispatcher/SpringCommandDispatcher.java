package com.cqshop.cqrs.common.dispatcher;

import com.cqshop.cqrs.common.command.AbstractApplicationCommand;
import com.cqshop.cqrs.common.command.ApplicationCommand;
import com.cqshop.cqrs.common.handler.CommandHandler;
import com.cqshop.cqrs.common.handler.CommandHandlerProvider;

/**
 * Created by Mateusz Brycki on 02/10/2018.
 */
public class SpringCommandDispatcher implements CommandDispatcher {

    private final CommandHandlerProvider commandHandlerProvider;

    public SpringCommandDispatcher(CommandHandlerProvider commandHandlerProvider) {
        this.commandHandlerProvider = commandHandlerProvider;
    }

    @Override
    public Object dispatch(AbstractApplicationCommand command) {

        CommandHandler<ApplicationCommand, Object> commandHandler = commandHandlerProvider.findHandler(command);
        Object result = commandHandler.handle(command);

        return result;
    }
}
