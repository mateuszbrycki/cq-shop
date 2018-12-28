package com.cqshop.order.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Mateusz Brycki on 28/12/2018.
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name="user_order")
public class Order {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long orderId;

    private Long orderOwner;

    @NotNull
    private Date creationDate;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "userOrder")
    private List<OrderLine> orderLines;

    public List<OrderLine> getOrderLines() {

        if (orderLines == null) {
            orderLines = new ArrayList<>();
        }

        return orderLines;
    }
}
