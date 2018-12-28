package com.cqshop.order.domain;

import lombok.*;

import javax.persistence.*;

/**
 * Created by Mateusz Brycki on 28/12/2018.
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table
public class OrderLine {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long orderLineId;

    private Long productId;

    private Double price;

    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id")
    private Order userOrder;

}
