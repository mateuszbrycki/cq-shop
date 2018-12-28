package com.cqshop.cart.domain.service;

import com.cqshop.cart.domain.Cart;
import com.cqshop.cart.domain.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by Mateusz Brycki on 28/12/2018.
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class CartEntityService {

    private final CartRepository cartRepository;

    public Optional<Cart> getUserCart(long userId) {
        return cartRepository.findByCartOwner(userId);
    }
}
