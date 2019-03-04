package com.cqshop.warehouse.dto;

import lombok.*;

/**
 * Created by Mateusz Brycki on 26/12/2018.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AddProduct {

    private String name;
    private String code;
    private String description;
    private Integer quantity;
    private Double price;
}
