package com.cqshop.cqrs.common.handler.command;

import com.cqshop.cqrs.common.command.ApplicationCommand;

/**
 * Created by Mateusz Brycki on 03/10/2018.
 */
public interface CommandHandler<T extends ApplicationCommand, W> {

    W handle(T command);

}
