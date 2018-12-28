package com.cqshop.warehouse.domain.repository;

import com.cqshop.warehouse.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Mateusz Brycki on 27/12/2018.
 */
@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findAllByProductProductId(Long productId);

    List<Reservation> findAllByProductProductIdAndUserId(Long productId, Long userId);
}
