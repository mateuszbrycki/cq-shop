package com.cqshop.cart.application.handler;

import com.cqshop.cart.application.query.FindUserCart;
import com.cqshop.cart.domain.repository.CartRepository;
import com.cqshop.cart.dto.Cart;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Created by Mateusz Brycki on 2019-02-19.
 */
@RunWith(MockitoJUnitRunner.class)
public class FindUserCartHandlerTest {

    @Mock
    private CartRepository cartRepository;

    @InjectMocks
    private FindUserCartHandler handler;

    @Test
    public void shouldReturnNoCart_noCartInDb() {
        //given
        when(cartRepository.findByCartOwner(1l)).thenReturn(Optional.empty());

        //when
        Optional<Cart> cart = handler.handle(new FindUserCart(1l));

        //then
        assertTrue(cart.isEmpty());
    }

    @Test
    public void shouldReturnCart() {
        //given
        when(cartRepository.findByCartOwner(1l)).thenReturn(Optional.of(
                com.cqshop.cart.domain.Cart.builder().cartLines(Collections.emptyList()).build()
        ));

        //when
        Optional<Cart> cart = handler.handle(new FindUserCart(1l));

        //then
        assertFalse(cart.isEmpty());
    }

}