package com.cqshop.order.domain.repository;

import com.cqshop.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Mateusz Brycki on 28/12/2018.
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
