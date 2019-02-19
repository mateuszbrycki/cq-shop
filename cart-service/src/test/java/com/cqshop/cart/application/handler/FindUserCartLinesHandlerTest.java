package com.cqshop.cart.application.handler;

import com.cqshop.cart.application.query.FindUserCartLines;
import com.cqshop.cart.domain.Cart;
import com.cqshop.cart.domain.repository.CartRepository;
import com.cqshop.cart.dto.CartLine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Created by Mateusz Brycki on 2019-02-19.
 */
@RunWith(MockitoJUnitRunner.class)
public class FindUserCartLinesHandlerTest {

    @Mock
    private CartRepository cartRepository;

    @InjectMocks
    private FindUserCartLinesHandler handler;

    @Test
    public void shouldReturnEmptyListOfCarts() {
        //given
        when(cartRepository.findByCartOwner(1l)).thenReturn(Optional.empty());

        //when
        List<CartLine> lines = handler.handle(new FindUserCartLines(1l));

        //then
        assertTrue(lines.isEmpty());
    }

    @Test
    public void shouldReturnOneCartLine() {
        //given
        when(cartRepository.findByCartOwner(1l)).thenReturn(
                Optional.of(Cart.builder().cartLines(
                        Arrays.asList(com.cqshop.cart.domain.CartLine.builder().build())
                ).build()));

        //when
        List<CartLine> lines = handler.handle(new FindUserCartLines(1l));

        //then
        assertFalse(lines.isEmpty());
        assertEquals(1, lines.size());
    }

    @Test
    public void shouldReturnTwoCartLines() {
        //given
        when(cartRepository.findByCartOwner(1l)).thenReturn(
                Optional.of(Cart.builder().cartLines(
                        Arrays.asList(
                                com.cqshop.cart.domain.CartLine.builder().build(),
                                com.cqshop.cart.domain.CartLine.builder().build()
                        )
                ).build()));

        //when
        List<CartLine> lines = handler.handle(new FindUserCartLines(1l));

        //then
        assertFalse(lines.isEmpty());
        assertEquals(2, lines.size());
    }

}