package com.cqshop.cart.domain.service;

import com.cqshop.cart.domain.Cart;
import com.cqshop.cart.domain.CartLine;
import com.cqshop.cart.domain.event.CartLineRemoved;
import com.cqshop.cart.domain.event.CartLineUpdated;
import com.cqshop.cart.domain.event.ProductAddedToCart;
import com.cqshop.cart.domain.event.ProductRemovedFromCart;
import com.cqshop.cart.domain.exception.CartNotFoundException;
import com.cqshop.cart.domain.repository.CartLineRepository;
import com.cqshop.cart.domain.repository.CartRepository;
import com.cqshop.cart.infrastructure.EventPublisher;
import com.cqshop.cqrs.common.command.ApplicationCommand;
import com.cqshop.cqrs.common.gate.Gate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Mateusz Brycki on 2019-02-10.
 */
@RunWith(MockitoJUnitRunner.class)
public class CartContentServiceTest {

    @Mock
    private Gate gate;

    @Mock
    private CartRepository cartRepository;

    @Mock
    private CartLineRepository cartLineRepository;

    @Mock
    private ReservationService reservationService;

    @Mock
    private EventPublisher eventPublisher;

    @InjectMocks
    private CartContentService cartContentService;

    @Test(expected = CartNotFoundException.class)
    public void shouldThrowCartNotFoundExceptionWhenAddingProductToCart() throws CartNotFoundException {
        //given
        when(cartRepository.findByCartOwner(1l)).thenReturn(Optional.empty());
        when(gate.dispatch(any(ApplicationCommand.class))).thenReturn(false);

        //when
        Boolean result = cartContentService.add(1l, 10, 1l);
    }

    @Test
    public void shouldCreateNewCartAndFailDueToReservationService() throws CartNotFoundException {
        //given
        when(cartRepository.findByCartOwner(1l)).thenReturn(Optional.empty(),
                Optional.of(Cart.builder().cartOwner(1l).cartId(1l).build()));
        when(gate.dispatch(any(ApplicationCommand.class))).thenReturn(true);
        when(reservationService.create(1l, 10, 1l)).thenReturn(false);

        //when
        Boolean result = cartContentService.add(1l, 10, 1l);

        //then
        assertFalse(result);
    }

    @Test
    public void shouldAddNewProductToCart() throws CartNotFoundException {
        //given
        Cart cart = Cart.builder().cartOwner(1l).cartId(1l).cartLines(new ArrayList<>()).build();

        when(cartRepository.findByCartOwner(1l)).thenReturn(Optional.of(cart));
        when(reservationService.create(1l, 10, 1l)).thenReturn(true);

        //when
        Boolean result = cartContentService.add(1l, 10, 1l);

        //then
        assertTrue(result);

        assertEquals(1, cart.getCartLines().size());
        assertEquals(1l, (long)cart.getCartLines().get(0).getProductId());
        assertEquals(10, (long)cart.getCartLines().get(0).getQuantity());

        verify(cartRepository).save(cart);
        verify(eventPublisher).publish(ProductAddedToCart.builder()
                .productId(1l)
                .cartId(cart.getCartId())
                .quantity(10)
                .price(100.)
                .build());
    }

    @Test
    public void shouldModifyExistingCartLine() throws CartNotFoundException {
        //given
        Cart cart = Cart.builder().cartOwner(1l).cartId(1l).cartLines(Arrays.asList(
                CartLine.builder().cartLineId(1l).price(100.).productId(1l).quantity(10).build()
        )).build();

        when(cartRepository.findByCartOwner(1l)).thenReturn(Optional.of(cart));
        when(reservationService.create(1l, 10, 1l)).thenReturn(true);


        //when
        Boolean result = cartContentService.add(1l, 10, 1l);

        //then
        assertTrue(result);

        assertEquals(1, cart.getCartLines().size());
        assertEquals(1l, (long)cart.getCartLines().get(0).getProductId());
        assertEquals(20, (long)cart.getCartLines().get(0).getQuantity());

        verify(cartRepository).save(cart);
        verify(eventPublisher).publish(CartLineUpdated.builder()
                .productId(1l)
                .cartLineId(1l)
                .quantity(10)
                .price(100.)
                .build());
    }

    @Test
    public void shouldModifyOnlyOneOfExistingCartLines() throws CartNotFoundException {
        //given
        Cart cart = Cart.builder().cartOwner(1l).cartId(1l).cartLines(Arrays.asList(
                CartLine.builder().cartLineId(1l).price(100.).productId(1l).quantity(10).build(),
                CartLine.builder().cartLineId(2l).price(99.).productId(2l).quantity(100).build()
        )).build();

        when(cartRepository.findByCartOwner(1l)).thenReturn(Optional.of(cart));
        when(reservationService.create(1l, 10, 1l)).thenReturn(true);


        //when
        Boolean result = cartContentService.add(1l, 10, 1l);

        //then
        assertTrue(result);

        assertEquals(2, cart.getCartLines().size());
        assertEquals(1l, (long)cart.getCartLines().get(0).getProductId());
        assertEquals(20, (long)cart.getCartLines().get(0).getQuantity());

        verify(cartRepository).save(cart);
        verify(eventPublisher).publish(CartLineUpdated.builder()
                .productId(1l)
                .cartLineId(1l)
                .quantity(10)
                .price(100.)
                .build());
    }


    @Test(expected = CartNotFoundException.class)
    public void shouldThrowCartNotFoundExceptionWhenRemovingProductFromCart() throws CartNotFoundException {
        //given
        when(cartRepository.findByCartOwner(1l)).thenReturn(Optional.empty());
        when(gate.dispatch(any(ApplicationCommand.class))).thenReturn(false);

        //when
        Boolean result = cartContentService.remove(1l, 1l);
    }

    @Test
    public void shouldNotRemoveProductFromCartWhenReservationHasNotBeenReleased() throws CartNotFoundException {
        //given
        when(cartRepository.findByCartOwner(1l)).thenReturn(Optional.of(Cart.builder().cartOwner(1l).cartId(1l).build()));
        when(reservationService.remove(1l, 1l)).thenReturn(false);

        //when
        Boolean result = cartContentService.remove(1l, 1l);

        //then
        assertFalse(result);
    }

    @Test
    public void shouldNotRemoveProductFromCartDueToMissingLine() throws CartNotFoundException {
        //given
        CartLine cartLine = CartLine.builder().cartLineId(1l).productId(2l).build();

        List<CartLine> cartLines = new ArrayList<>();
        cartLines.add(cartLine);

        Cart cart = Cart.builder().cartOwner(1l).cartId(1l).cartLines(cartLines).build();
        when(cartRepository.findByCartOwner(1l)).thenReturn(Optional.of(cart));
        when(reservationService.remove(1l, 1l)).thenReturn(true);

        //when
        Boolean result = cartContentService.remove(1l, 1l);

        //then
        assertFalse(result);
        assertEquals(1, cart.getCartLines().size());
    }

    @Test
    public void shouldRemoveProductFromCart() throws CartNotFoundException {
        //given
        CartLine cartLine = CartLine.builder().cartLineId(1l).productId(1l).build();

        List<CartLine> cartLines = new ArrayList<>();
        cartLines.add(cartLine);

        Cart cart = Cart.builder().cartOwner(1l).cartId(1l).cartLines(cartLines).build();
        when(cartRepository.findByCartOwner(1l)).thenReturn(Optional.of(cart));
        when(reservationService.remove(1l, 1l)).thenReturn(true);

        //when
        Boolean result = cartContentService.remove(1l, 1l);

        //then
        assertTrue(result);

        assertEquals(0, cart.getCartLines().size());
        verify(cartRepository).save(cart);

        verify(eventPublisher).publish(ProductRemovedFromCart.builder()
                .productId(1l)
                .cartLineId(cartLine.getCartLineId())
                .cartId(cart.getCartId())
                .build());
    }

    @Test
    public void shouldRemoveOnlyCartLine() throws CartNotFoundException {
        //given
        CartLine cartLine = CartLine.builder().cartLineId(1l).productId(1l).build();
        CartLine cartLine2 = CartLine.builder().cartLineId(2l).productId(2l).build();

        List<CartLine> cartLines = new ArrayList<>();
        cartLines.add(cartLine);
        cartLines.add(cartLine2);

        Cart cart = Cart.builder().cartOwner(1l).cartId(1l).cartLines(cartLines).build();
        when(cartRepository.findByCartOwner(1l)).thenReturn(Optional.of(cart));
        when(reservationService.remove(1l, 1l)).thenReturn(true);

        //when
        Boolean result = cartContentService.remove(1l, 1l);

        //then
        assertTrue(result);

        assertEquals(1, cart.getCartLines().size());
        verify(cartRepository).save(cart);

        verify(eventPublisher).publish(ProductRemovedFromCart.builder()
                .productId(1l)
                .cartLineId(cartLine.getCartLineId())
                .cartId(cart.getCartId())
                .build());
    }

    @Test(expected = CartNotFoundException.class)
    public void shouldNotRemoveAllCartLinesDueToMissingCart() throws CartNotFoundException {

        when(cartRepository.findByCartOwner(1l)).thenReturn(Optional.empty());
        Boolean result = cartContentService.removeCartLines(1l);
    }

    @Test
    public void shouldRemoveAllCartLines() throws CartNotFoundException {

        CartLine cartLine = CartLine.builder().cartLineId(1l).price(100.).quantity(100).productId(1l).build();
        CartLine cartLine2 = CartLine.builder().cartLineId(2l).price(90.).quantity(90).productId(2l).build();

        List<CartLine> cartLines = new ArrayList<>();
        cartLines.add(cartLine);
        cartLines.add(cartLine2);

        Cart cart = Cart.builder().cartOwner(1l).cartId(1l).cartLines(cartLines).build();
        when(cartRepository.findByCartOwner(1l)).thenReturn(Optional.of(cart));

        Boolean result = cartContentService.removeCartLines(1l);

        verify(eventPublisher).publish(CartLineRemoved.builder()
                .cartLineId(cartLine.getCartLineId())
                .productId(cartLine.getProductId())
                .userId(1l)
                .quantity(cartLine.getQuantity())
                .price(cartLine.getPrice())
                .build());

        verify(eventPublisher).publish(CartLineRemoved.builder()
                .cartLineId(cartLine2.getCartLineId())
                .productId(cartLine2.getProductId())
                .userId(1l)
                .quantity(cartLine2.getQuantity())
                .price(cartLine2.getPrice())
                .build());
    }




}