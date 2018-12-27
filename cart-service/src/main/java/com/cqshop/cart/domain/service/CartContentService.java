package com.cqshop.cart.domain.service;

import com.cqshop.cart.application.command.CartCreationRequested;
import com.cqshop.cart.domain.Cart;
import com.cqshop.cart.domain.CartLine;
import com.cqshop.cart.domain.event.CartLineUpdated;
import com.cqshop.cart.domain.event.ProductAddedToCart;
import com.cqshop.cart.domain.repository.CartRepository;
import com.cqshop.cart.infrastructure.EventPublisher;
import com.cqshop.cqrs.common.gate.Gate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by Mateusz Brycki on 27/12/2018.
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class CartContentService {

    private final Gate gate;
    private final CartRepository cartRepository;
    private final ReservationService reservationService;
    private final EventPublisher eventPublisher;

    public Boolean add(long productId, int quantity, long userId) {

        //TODO mbrycki check if product has already been added to the cart

        Optional<Cart> optionalCart = getUserCart(userId);
        if (!optionalCart.isPresent()) {
            log.error("Cannot find a cart for user {}", userId);
            return false;
        }
        Cart cart = optionalCart.get();

        boolean isReserved = reservationService.reserve(productId, quantity, userId);
        if (!isReserved) {
            log.error("Cannot reserve a product {} in quantity of {}", productId, quantity);
            return false;
        }

        updateCart(cart, productId, quantity);
        return true;
    }

    private void updateCart(Cart cart, long productId, int quantity) {

        if (cart.getCartLines() != null) {
            for (CartLine cartLine : cart.getCartLines()) {
                if (cartLine.getProductId().equals(productId)) {
                    cartLine.setQuantity(cartLine.getQuantity() + quantity);

                    log.info("Updated the cart line ({}) with product {} and quantity {}", cartLine.getCartLineId(), productId, quantity);
                    cartRepository.save(cart);

                    eventPublisher.publish(CartLineUpdated.builder()
                            .productId(productId)
                            .cartLineId(cartLine.getCartLineId())
                            .quantity(quantity)
                            .price(cartLine.getPrice())
                            .build());

                    return;
                }
            }
        }

        CartLine cartLine = CartLine.builder()
                .productId(productId)
                .quantity(quantity)
                .price(100.) //TODO mbrycki retrieve product's price
                .cart(cart)
                .build();

        cart.getCartLines().add(cartLine);
        cartRepository.save(cart);

        log.info("Created the cart line ({}) with product {} and quantity {}", cartLine.getCartLineId(), productId, quantity);

        eventPublisher.publish(ProductAddedToCart.builder()
                .productId(productId)
                .cartId(cart.getCartId())
                .quantity(quantity)
                .price(cartLine.getPrice())
                .build());
    }

    private Optional<Cart> getUserCart(long userId) {
        Optional<Cart> cart = cartRepository.findByCartOwner(userId);

        if (!cart.isPresent()) {
            boolean cartCreationResult = createCartForUser(userId);

            if (cartCreationResult) {
                cart = cartRepository.findByCartOwner(userId);
            }
        }

        return cart;
    }

    private Boolean createCartForUser(long userId) {
        return gate.dispatch(
                CartCreationRequested.builder()
                        .userId(userId)
                        .build()
        );
    }
}
