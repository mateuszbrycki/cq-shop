package com.cqshop.cart.domain.repository;

import com.cqshop.cart.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by Mateusz Brycki on 25/12/2018.
 */
@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findByCartOwner(Long cartOwner);

}


