package com.cqshop.cart.application.handler;

import com.cqshop.cart.application.command.ProductRemovalFromCartRequested;
import com.cqshop.cart.domain.exception.CartNotFoundException;
import com.cqshop.cart.domain.service.CartContentService;
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
public class ProductRemovalFromCartRequestedHandler implements CommandHandler<ProductRemovalFromCartRequested, Boolean> {

    private final CartContentService cartContentService;

    @Override
    public Boolean handle(ProductRemovalFromCartRequested productRemovalFromCartRequested) {

        log.info("Received productRemovalFromCartRequested: " + productRemovalFromCartRequested);


        try {
            return cartContentService.remove(
                    productRemovalFromCartRequested.getProductId(),
                    productRemovalFromCartRequested.getUserId()
            );
        } catch (CartNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
