package com.cqshop.cart.domain.service;

import com.cqshop.cart.domain.Cart;
import com.cqshop.cart.domain.event.UserCartCreated;
import com.cqshop.cart.domain.repository.CartRepository;
import com.cqshop.cart.infrastructure.EventPublisher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Mateusz Brycki on 2019-02-10.
 */
@RunWith(MockitoJUnitRunner.class)
public class CartCreationServiceTest {

    @Mock
    private CartRepository cartRepository;

    @Mock
    private EventPublisher eventPublisher;

    @InjectMocks
    private CartCreationService cartCreationService;

    @Test
    public void shouldCreateCartAndPublishEvent() {

        Cart cart = Cart.builder().cartOwner(1l).cartId(1l).build();
        when(cartRepository.save(any())).thenReturn(cart);

        Boolean result = cartCreationService.createCart(1l);
        assertTrue(result);

        verify(eventPublisher).publish(UserCartCreated.builder()
                .userId(cart.getCartOwner())
                .cartId(cart.getCartId())
                .build());
    }
}