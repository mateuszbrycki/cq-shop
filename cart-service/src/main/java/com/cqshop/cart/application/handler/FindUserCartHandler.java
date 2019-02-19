package com.cqshop.cart.application.handler;

import com.cqshop.cart.application.query.FindUserCart;
import com.cqshop.cart.domain.Cart;
import com.cqshop.cart.domain.repository.CartRepository;
import com.cqshop.cqrs.common.handler.query.QueryHandler;
import com.cqshop.cqrs.common.handler.query.QueryHandlerAnnotation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by Mateusz Brycki on 2019-02-19.
 */
@AllArgsConstructor
@Component
@QueryHandlerAnnotation
public class FindUserCartHandler implements QueryHandler<FindUserCart, Optional<com.cqshop.cart.dto.Cart>> {

    private CartRepository cartRepository;

    @Override
    public Optional<com.cqshop.cart.dto.Cart> handle(FindUserCart findUserCart) {
        Optional<Cart> userCart = cartRepository.findByCartOwner(findUserCart.getUserId());

        if (userCart.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(com.cqshop.cart.dto.Cart.of(userCart.get()));

    }
}
