package com.cqshop.warehouse.domain.repository;

import com.cqshop.warehouse.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Mateusz Brycki on 26/12/2018.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByCode(String code);
}
