package com.cqshop.cart.application.handler;

import com.cqshop.cart.application.command.CartCreationRequested;
import com.cqshop.cart.domain.service.CartCreationService;
import com.cqshop.cqrs.common.handler.command.CommandHandler;
import com.cqshop.cqrs.common.handler.command.CommandHandlerAnnotation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Mateusz Brycki on 25/12/2018.
 */
@Slf4j
@RequiredArgsConstructor
@CommandHandlerAnnotation
public class CartCreationRequestedHandler implements CommandHandler<CartCreationRequested, Boolean> {

    private final CartCreationService cartCreationService;

    @Override
    public Boolean handle(CartCreationRequested cartForNewUserCreationRequested) {

        log.info("Received cartForNewUserCreationRequested: " + cartForNewUserCreationRequested);

        return cartCreationService.createCart(cartForNewUserCreationRequested.getUserId()); }
}
