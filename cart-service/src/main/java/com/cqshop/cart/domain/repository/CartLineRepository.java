package com.cqshop.cart.domain.repository;

import com.cqshop.cart.domain.CartLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Mateusz Brycki on 28/12/2018.
 */
@Repository
public interface CartLineRepository  extends JpaRepository<CartLine, Long> {
}
