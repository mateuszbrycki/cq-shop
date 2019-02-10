package com.cqshop.warehouse.domain.service;

import com.cqshop.warehouse.domain.Product;
import com.cqshop.warehouse.domain.Reservation;
import com.cqshop.warehouse.domain.event.ProductReservationCreated;
import com.cqshop.warehouse.domain.event.ProductReservationRemoved;
import com.cqshop.warehouse.domain.repository.ProductRepository;
import com.cqshop.warehouse.domain.repository.ReservationRepository;
import com.cqshop.warehouse.infrastructure.EventPublisher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Mateusz Brycki on 2019-02-10.
 */
@RunWith(MockitoJUnitRunner.class)
public class ProductReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private EventPublisher eventPublisher;

    @InjectMocks
    private ProductReservationService productReservationService;

    @Test
    public void shouldNotRemoveNonExistingReservation() {
        //given
        when(reservationRepository.findAllByProductProductIdAndUserId(1l, 1l)).thenReturn(Collections.emptyList());

        //when
        boolean result = productReservationService.remove(1l, 1l);

        //then
        assertFalse(result);
    }

    @Test
    public void shouldRemoveReservationForProductAndUser() {
        //given
        when(reservationRepository.findAllByProductProductIdAndUserId(1l, 1l)).thenReturn(Arrays.asList(
                Reservation.builder().reservationId(1l).userId(1l).quantity(10).product(Product.builder().productId(1l).build()).build(),
                Reservation.builder().reservationId(2l).userId(1l).quantity(100).product(Product.builder().productId(1l).build()).build()
        ));

        //when
        boolean result = productReservationService.remove(1l, 1l);

        //then
        assertTrue(result);

        verify(eventPublisher).publish(
                ProductReservationRemoved.builder()
                .userId(1l)
                .productId(1l)
                .id(1l)
                .build());

        verify(eventPublisher).publish(
                ProductReservationRemoved.builder()
                .userId(1l)
                .productId(1l)
                .id(2l)
                .build());
    }

    @Test
    public void shouldNotCreateReservationForNotExistingProduct() {
        //given
        when(productRepository.findById(1l)).thenReturn(Optional.empty());

        //when
        boolean result = productReservationService.reserve(1l, 10, 1l);

        //then
        assertFalse(result);
    }

    @Test
    public void shouldNotCreateReservationWhenThereIsNoEnoughProductsInStock() {
        //given
        when(productRepository.findById(1l)).thenReturn(Optional.of(
                Product.builder().quantity(9).build()
        ));

        //when
        boolean result = productReservationService.reserve(1l, 10, 1l);

        //then
        assertFalse(result);
    }

    @Test
    public void shouldCreateReservation() {
        //given
        when(productRepository.findById(1l)).thenReturn(Optional.of(
                Product.builder().productId(1l).quantity(10).build()
        ));

        when(reservationRepository.save(any(Reservation.class)))
                .thenReturn(
                        Reservation.builder().reservationId(1l).build()
                );

        //when
        boolean result = productReservationService.reserve(1l, 10, 1l);

        //then
        assertTrue(result);

        verify(eventPublisher).publish(
                ProductReservationCreated.builder()
                        .id(1l)
                        .productId(1l)
                        .quantity(10)
                        .userId(1l)
                        .build());
    }



}