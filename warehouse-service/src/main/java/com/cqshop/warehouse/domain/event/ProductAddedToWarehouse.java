package com.cqshop.warehouse.domain.event;

import com.cqshop.kafka.event.Event;
import lombok.*;

/**
 * Created by Mateusz Brycki on 26/12/2018.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class ProductAddedToWarehouse extends Event {

    private Long id;
    private String code;
    private String name;
    private int quantity;
    private Double price;
}
