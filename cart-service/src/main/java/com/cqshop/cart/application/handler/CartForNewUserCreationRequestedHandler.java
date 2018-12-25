package com.cqshop.cart.application.handler;

import com.cqshop.cart.application.command.CartForNewUserCreationRequested;
import com.cqshop.cart.domain.service.CartCreationService;
import com.cqshop.cqrs.common.handler.CommandHandler;
import com.cqshop.cqrs.common.handler.CommandHandlerAnnotation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Mateusz Brycki on 25/12/2018.
 */
@Slf4j
@RequiredArgsConstructor
@CommandHandlerAnnotation
public class CartForNewUserCreationRequestedHandler implements CommandHandler<CartForNewUserCreationRequested, Boolean> {

    private final CartCreationService cartCreationService;

    @Override
    public Boolean handle(CartForNewUserCreationRequested cartForNewUserCreationRequested) {

        log.info("Received cartForNewUserCreationRequested: " + cartForNewUserCreationRequested);

        return cartCreationService.createCart(cartForNewUserCreationRequested.getUserId()); }
}
