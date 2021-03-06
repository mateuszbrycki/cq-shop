package com.cqshop.warehouse.domain.repository;

import com.cqshop.warehouse.domain.MaterializedReservation;
import com.cqshop.warehouse.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Mateusz Brycki on 27/12/2018.
 */
@Repository
public interface MaterializedReservationRepository extends JpaRepository<MaterializedReservation, Long> {
}
