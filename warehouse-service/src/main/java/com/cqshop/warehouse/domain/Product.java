package com.cqshop.warehouse.domain;

import lombok.*;

import javax.persistence.*;

/**
 * Created by Mateusz Brycki on 26/12/2018.
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name="product")
@EqualsAndHashCode
public class Product {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long productId;

    private String name;
    private String code;
    private String description;
    private Integer quantity;
    private Double price;
}
