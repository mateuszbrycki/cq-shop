package com.cqshop.cart.domain;

import lombok.*;

import javax.persistence.*;

/**
 * Created by Mateusz Brycki on 27/12/2018.
 */

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table
public class CartLine {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long cartLineId;

    private Long productId;

    private Double price;

    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cart_id")
    private Cart cart;
}
