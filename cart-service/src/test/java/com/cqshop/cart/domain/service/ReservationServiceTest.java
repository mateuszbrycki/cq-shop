package com.cqshop.cart.domain.service;

import com.cqshop.cart.domain.repository.WarehouseRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by Mateusz Brycki on 2019-02-10.
 */
@RunWith(MockitoJUnitRunner.class)
public class ReservationServiceTest {

    @Mock
    private WarehouseRepository warehouseRespository;

    @InjectMocks
    private ReservationService reservationService;

    @Test
    public void shouldCreateReservation() {
        when(warehouseRespository.reserve(any())).thenReturn(HttpStatus.OK);
        boolean result = reservationService.create(1l, 10, 1l);
        assertTrue(result);
    }

    @Test
    public void shouldNotCreateReservation() {
        when(warehouseRespository.reserve(any())).thenReturn(HttpStatus.BAD_REQUEST);
        boolean result = reservationService.create(1l, 10, 1l);
        assertFalse(result);
    }

    @Test
    public void shouldNotRemoveReservation() {
        when(warehouseRespository.remove(1l, 1l)).thenReturn(HttpStatus.BAD_REQUEST);
        boolean result = reservationService.remove(1l,  1l);
        assertFalse(result);
    }

    @Test
    public void shouldRemoveReservation() {
        when(warehouseRespository.remove(1l, 1l)).thenReturn(HttpStatus.OK);
        boolean result = reservationService.remove(1l,  1l);
        assertTrue(result);
    }
}