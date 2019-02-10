package com.cqshop.cart.application.handler;

import com.cqshop.cart.application.command.RemoveCartLines;
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
public class RemoveCartLinesHandler implements CommandHandler<RemoveCartLines, Boolean> {

    private final CartContentService cartContentService;

    @Override
    public Boolean handle(RemoveCartLines removeCartLines) {

        log.info("Received removeCartLines: " + removeCartLines);


        try {
            return cartContentService.removeCartLines(
                    removeCartLines.getUserId()
            );
        } catch (CartNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
