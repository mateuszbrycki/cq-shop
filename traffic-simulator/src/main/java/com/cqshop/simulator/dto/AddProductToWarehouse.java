package com.cqshop.simulator.dto;

import lombok.*;

/**
 * Created by Mateusz Brycki on 2019-02-09.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class AddProductToWarehouse {

    private String name;
    private String code;
    private String description;
    private int quantity;
    private double price;
}
