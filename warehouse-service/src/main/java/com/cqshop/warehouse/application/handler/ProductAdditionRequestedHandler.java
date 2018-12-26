package com.cqshop.warehouse.application.handler;

import com.cqshop.cqrs.common.handler.CommandHandler;
import com.cqshop.cqrs.common.handler.CommandHandlerAnnotation;
import com.cqshop.warehouse.application.command.ProductAdditionRequested;
import com.cqshop.warehouse.domain.Product;
import com.cqshop.warehouse.domain.service.ProductAddingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
/**
 * Created by Mateusz Brycki on 26/12/2018.
 */
@Slf4j
@RequiredArgsConstructor
@CommandHandlerAnnotation
public class ProductAdditionRequestedHandler implements CommandHandler<ProductAdditionRequested, Boolean> {

    private final ProductAddingService productAddingService;

    @Override
    public Boolean handle(ProductAdditionRequested productAdditionRequested) {

        log.info("Received productAdditionRequested: " + productAdditionRequested);

        Product product = Product.builder()
                .code(productAdditionRequested.getCode())
                .description(productAdditionRequested.getDescription())
                .name(productAdditionRequested.getName())
                .build();

        productAddingService.addProduct(product, productAdditionRequested.getQuantity());

        return true;
    }

}
