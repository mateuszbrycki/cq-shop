package com.cqshop.cart.domain.service;

import com.cqshop.cart.domain.Cart;
import com.cqshop.cart.domain.event.UserCartCreated;
import com.cqshop.cart.domain.repository.CartRepository;
import com.cqshop.cart.infrastructure.EventPublisher;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

/**
 * Created by Mateusz Brycki on 25/12/2018.
 */
@AllArgsConstructor
@Service
public class CartCreationService {

    private final CartRepository cartRepository;
    private final EventPublisher eventPublisher;


    @Transactional
    public Boolean createCart(Long userId) {

        Cart cart = Cart.builder()
                .cartOwner(userId)
                .creationDate(new Date(System.currentTimeMillis()))
                .build();

        cart = cartRepository.save(cart);

        eventPublisher.publish(
                UserCartCreated.builder()
                .userId(cart.getCartOwner())
                .cartId(cart.getCartId())
                .build()
        );

        return true;
    }
}
