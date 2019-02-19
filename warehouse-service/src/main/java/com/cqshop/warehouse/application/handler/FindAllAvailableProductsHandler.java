package com.cqshop.warehouse.application.handler;

import com.cqshop.cqrs.common.handler.command.CommandHandler;
import com.cqshop.cqrs.common.handler.command.CommandHandlerAnnotation;
import com.cqshop.cqrs.common.handler.query.QueryHandler;
import com.cqshop.cqrs.common.handler.query.QueryHandlerAnnotation;
import com.cqshop.warehouse.application.command.MaterializeUserReservations;
import com.cqshop.warehouse.application.query.FindAllAvailableProducts;
import com.cqshop.warehouse.domain.repository.ProductRepository;
import com.cqshop.warehouse.dto.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Mateusz Brycki on 2019-02-19.
 */
@Slf4j
@RequiredArgsConstructor
@QueryHandlerAnnotation
public class FindAllAvailableProductsHandler  implements QueryHandler<FindAllAvailableProducts, List<Product>> {

    private final ProductRepository productRepository;

    @Override
    public List<Product> handle(FindAllAvailableProducts findAllAvailableProducts) {
        return productRepository.findAll()
                .stream()
                .filter(product -> product.getQuantity() > 0)
                .map(com.cqshop.warehouse.dto.Product::of)
                .collect(Collectors.toList());
    }
}
