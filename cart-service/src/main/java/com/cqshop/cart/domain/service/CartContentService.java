package com.cqshop.cart.domain.service;

import com.cqshop.cart.application.command.CartCreationRequested;
import com.cqshop.cart.domain.Cart;
import com.cqshop.cart.domain.CartLine;
import com.cqshop.cart.domain.event.CartLineRemoved;
import com.cqshop.cart.domain.event.CartLineUpdated;
import com.cqshop.cart.domain.event.ProductAddedToCart;
import com.cqshop.cart.domain.event.ProductRemovedFromCart;
import com.cqshop.cart.domain.repository.CartLineRepository;
import com.cqshop.cart.domain.repository.CartRepository;
import com.cqshop.cart.infrastructure.EventPublisher;
import com.cqshop.cqrs.common.gate.Gate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
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
    private final CartLineRepository cartLineRepository;
    private final ReservationService reservationService;
    private final EventPublisher eventPublisher;

    public Boolean add(long productId, int quantity, long userId) {

        Optional<Cart> optionalCart = getUserCart(userId);
        if (!optionalCart.isPresent()) {
            log.error("Cannot find a cart for user {}", userId);
            return false;
        }
        Cart cart = optionalCart.get();

        boolean isReserved = reservationService.create(productId, quantity, userId);
        if (!isReserved) {
            log.error("Cannot create a product {} in quantity of {}", productId, quantity);
            return false;
        }

        addProductToCart(cart, productId, quantity);
        return true;
    }

    public Boolean remove(long productId, long userId) {

        Optional<Cart> optionalCart = getUserCart(userId);
        if (!optionalCart.isPresent()) {
            log.error("Cannot find a cart for user {}", userId);
            return false;
        }
        Cart cart = optionalCart.get();

        boolean isReservationReleased = reservationService.remove(productId, userId);
        if (!isReservationReleased) {
            log.error("Cannot release the reservation for a product {} in quantity of {}", productId);
            return false;
        }

        return removeProductFromCart(cart, productId);
    }

    private boolean removeProductFromCart(Cart cart, long productId) {
        Optional<CartLine> cartLineToRemove = cart.getCartLines().stream()
                .filter(line -> line.getProductId().equals(productId))
                .findFirst();


        if (cartLineToRemove.isPresent()) {
            CartLine line = cartLineToRemove.get();
            cart.getCartLines().remove(line);
            cartRepository.save(cart);

            log.info("Removed the cart line ({}) with product {} ", line.getCartLineId(), productId);

            eventPublisher.publish(ProductRemovedFromCart.builder()
                    .productId(productId)
                    .cartLineId(line.getCartLineId())
                    .cartId(cart.getCartId())
                    .build());

            return true;
        }

        return false;

    }

    private void addProductToCart(Cart cart, long productId, int quantity) {

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

    public Boolean removeCartLines(long userId) {
        Optional<Cart> cart = cartRepository.findByCartOwner(userId);

        if (!cart.isPresent()) {
            log.error("Cannot find a cart for user {}", userId);
            return false;
        }

        for (CartLine cartLine : cart.get().getCartLines()) {
            cartLineRepository.delete(cartLine);

            eventPublisher.publish(
                    CartLineRemoved.builder()
                            .cartLineId(cartLine.getCartLineId())
                            .productId(cartLine.getProductId())
                            .userId(userId)
                            .quantity(cartLine.getQuantity())
                            .price(cartLine.getPrice())
                            .build()
            );
        }

        return true;
    }
}
