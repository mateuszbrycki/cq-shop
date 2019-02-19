package com.cqshop.cart.application.handler;

import com.cqshop.cart.application.query.FindUserCartLines;
import com.cqshop.cart.domain.Cart;
import com.cqshop.cart.domain.repository.CartRepository;
import com.cqshop.cart.dto.CartLine;
import com.cqshop.cqrs.common.handler.query.QueryHandler;
import com.cqshop.cqrs.common.handler.query.QueryHandlerAnnotation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Mateusz Brycki on 2019-02-19.
 */
@AllArgsConstructor
@Component
@QueryHandlerAnnotation
public class FindUserCartLinesHandler implements QueryHandler<FindUserCartLines, List<CartLine>> {

    private CartRepository cartRepository;

    @Override
    public List<CartLine> handle(FindUserCartLines findUserCartLines) {

            Optional<Cart> cart = cartRepository.findByCartOwner(findUserCartLines.getUserId());

            if (cart.isEmpty()) {
                return Collections.emptyList();
            }

            return cart.get().getCartLines()
                    .stream()
                    .map(CartLine::of)
                    .collect(Collectors.toList());
    }
}
