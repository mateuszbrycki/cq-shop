package com.cqshop.warehouse.domain.service;

import com.cqshop.warehouse.domain.MaterializedReservation;
import com.cqshop.warehouse.domain.Product;
import com.cqshop.warehouse.domain.Reservation;
import com.cqshop.warehouse.domain.event.ReservationMaterialized;
import com.cqshop.warehouse.domain.repository.MaterializedReservationRepository;
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
import java.util.Date;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

/**
 * Created by Mateusz Brycki on 2019-02-10.
 */
@RunWith(MockitoJUnitRunner.class)
public class MaterializationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private MaterializedReservationRepository materializedReservationRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private EventPublisher eventPublisher;

    @InjectMocks
    private MaterializationService materializationService;

    @Test
    public void shouldNotMaterializeNonExistingReservations() {
        //given
        when(reservationRepository.findAllByUserId(1l)).thenReturn(Collections.emptyList());

        //when
        boolean result = materializationService.materializeReservationsForUser(1l);

        //then
        assertFalse(result);
    }

    @Test
    public void shouldMaterializeExistingReservationsAndPublishEvents() {
        //given

        Product product1 = Product.builder().productId(1l).quantity(10).build();
        Product product2 = Product.builder().productId(2l).quantity(100).build();

        Reservation reservation1 = Reservation.builder().reservationId(1l).product(product1).userId(1l).quantity(9).build();
        Reservation reservation2 = Reservation.builder().reservationId(2l).product(product2).userId(1l).quantity(90).build();


        when(reservationRepository.findAllByUserId(1l)).thenReturn(Arrays.asList(reservation1, reservation2));


        MaterializedReservation materializedReservation1 =
                MaterializedReservation.builder()
                        .materializationDate(new Date(System.currentTimeMillis()))
                        .build();

        MaterializedReservation materializedReservation2 =
                MaterializedReservation.builder()
                        .materializationDate(new Date(System.currentTimeMillis()))
                        .build();

        when(materializedReservationRepository.save(any(MaterializedReservation.class))).thenReturn(materializedReservation1, materializedReservation2);

        //when
        boolean result = materializationService.materializeReservationsForUser(1l);

        //then
        assertTrue(result);
        assertOldReservationsWereRemoved(reservation1, reservation2);
        assertReservationsHaveBeenMaterialized();
        assertRealProductQuantityHasBeenAdjusted();
        assertEventsHaveBeenPublished(materializedReservation1, materializedReservation2);

    }

    private void assertEventsHaveBeenPublished(MaterializedReservation materializedReservation1, MaterializedReservation materializedReservation2) {
        verify(eventPublisher).publish(ReservationMaterialized.builder()
                .reservationId(1l)
                .productId(1l)
                .quantity(9)
                .userId(1l)
                .materializationDate(materializedReservation1.getMaterializationDate().getTime())
                .build());

        verify(eventPublisher).publish(ReservationMaterialized.builder()
                .reservationId(2l)
                .productId(2l)
                .quantity(90)
                .userId(1l)
                .materializationDate(materializedReservation2.getMaterializationDate().getTime())
                .build());
    }

    private void assertRealProductQuantityHasBeenAdjusted() {
        verify(productRepository).save(Product.builder().productId(1l).quantity(1).build());
        verify(productRepository).save(Product.builder().productId(2l).quantity(10).build());
    }

    private void assertReservationsHaveBeenMaterialized() {
        verify(materializedReservationRepository, times(2)).save(any(MaterializedReservation.class));
    }

    private void assertOldReservationsWereRemoved(Reservation reservation1, Reservation reservation2) {
        verify(reservationRepository).delete(reservation1);
        verify(reservationRepository).delete(reservation2);
    }
}