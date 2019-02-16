package com.cqshop.cart.application.handler;

import com.cqshop.cart.application.command.AddingProductToCartRequested;
import com.cqshop.cart.domain.exception.CartNotFoundException;
import com.cqshop.cart.domain.service.CartContentService;
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
public class AddingProductToCartRequestedHandler implements CommandHandler<AddingProductToCartRequested, Boolean> {

    private final CartContentService cartContentService;

    @Override
    public Boolean handle(AddingProductToCartRequested addingProductToCartRequested) {

        log.info("Received addingProductToCartRequested: " + addingProductToCartRequested);


        try {
            return cartContentService.add(
                    addingProductToCartRequested.getProductId(),
                    addingProductToCartRequested.getQuantity(),
                    addingProductToCartRequested.getUserId()
            );
        } catch (CartNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
