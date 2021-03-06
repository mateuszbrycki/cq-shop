package com.cqshop.cart.domain.service;

import com.cqshop.cart.application.command.CartCreationRequested;
import com.cqshop.cart.domain.Cart;
import com.cqshop.cart.domain.CartLine;
import com.cqshop.cart.domain.event.CartLineRemoved;
import com.cqshop.cart.domain.event.CartLineUpdated;
import com.cqshop.cart.domain.event.ProductAddedToCart;
import com.cqshop.cart.domain.event.ProductRemovedFromCart;
import com.cqshop.cart.domain.exception.CartNotFoundException;
import com.cqshop.cart.domain.repository.CartLineRepository;
import com.cqshop.cart.domain.repository.CartRepository;
import com.cqshop.cart.domain.repository.WarehouseRepository;
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
    private final CartLineRepository cartLineRepository;
    private final ReservationService reservationService;
    private final WarehouseRepository warehouseRepository;
    private final EventPublisher eventPublisher;

    public Boolean add(long productId, int quantity, long userId) throws CartNotFoundException {

        Cart cart = getUserCart(userId)
                .orElseThrow(() -> new CartNotFoundException("Cannot find cart for user " + userId));

        boolean isReserved = reservationService.create(productId, quantity, userId);
        if (!isReserved) {
            log.error("Cannot create a product {} in quantity of {}", productId, quantity);
            return false;
        }

        addProductToCart(cart, productId, quantity);
        return true;
    }

    public Boolean remove(long productId, long userId) throws CartNotFoundException {

        Cart cart = getUserCart(userId)
                .orElseThrow(() -> new CartNotFoundException("Cannot find cart for user " + userId));

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

        double price = getProductPrice(productId, quantity);

        for (CartLine cartLine : cart.getCartLines()) {
            if (cartLine.getProductId().equals(productId)) {
                cartLine.setQuantity(cartLine.getQuantity() + quantity);

                log.info("Updated the cart line ({}) with product {} and quantity {}", cartLine.getCartLineId(), productId, quantity);
                cartRepository.save(cart);

                eventPublisher.publish(CartLineUpdated.builder()
                        .productId(productId)
                        .cartLineId(cartLine.getCartLineId())
                        .quantity(quantity)
                        .price(cartLine.getPrice() + price)
                        .build());

                return;
            }
        }


        CartLine cartLine = CartLine.builder()
                .productId(productId)
                .quantity(quantity)
                .price(price)
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

    private double getProductPrice(long productId, int quantity) {
        double price = warehouseRepository.findProduct(productId).getBody().getPrice();
        if (price == 0.) {
            log.error("Got 0 price for {}", productId);
        }

        return price * quantity;
    }

    private Optional<Cart> getUserCart(long userId) {
        Optional<Cart> cart = cartRepository.findByCartOwner(userId);

        if (cart.isEmpty()) {
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

    public Boolean removeCartLines(long userId) throws CartNotFoundException {
        Cart cart = cartRepository.findByCartOwner(userId)
                .orElseThrow(() -> new CartNotFoundException("Cannot find cart for user " + userId));

        for (CartLine cartLine : cart.getCartLines()) {
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
