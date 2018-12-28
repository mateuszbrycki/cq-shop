package com.cqshop.warehouse.domain;

import lombok.*;
import org.joda.time.DateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

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
public class MaterializedReservation {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long reservationId;

    @NotNull
    private Long originalTemporaryReservation;

    @NotNull
    private Integer quantity;

    @NotNull
    private Date reservationDate;

    @NotNull
    private Date materializationDate;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "product_id")
    private Product product;

    private Long userId;

    public static MaterializedReservation of(Reservation reservation) {
        return MaterializedReservation.builder()
                .originalTemporaryReservation(reservation.getReservationId())
                .quantity(reservation.getQuantity())
                .product(reservation.getProduct())
                .reservationDate(reservation.getReservationDate())
                .materializationDate(DateTime.now().toDate())
                .userId(reservation.getUserId())
                .build();
    }
}
