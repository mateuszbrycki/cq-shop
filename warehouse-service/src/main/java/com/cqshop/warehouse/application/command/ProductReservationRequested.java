package com.cqshop.warehouse.application.command;

import com.cqshop.cqrs.common.command.AbstractApplicationCommandWithTimestamp;
import com.cqshop.cqrs.common.command.ApplicationCommandAnnotation;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Mateusz Brycki on 26/12/2018.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@ApplicationCommandAnnotation
@Builder
public class ProductReservationRequested extends AbstractApplicationCommandWithTimestamp implements Serializable {

    @NotNull
    private Long productId;

    @NotNull
    private Long userId;

    @NotNull
    private Integer quantity;

}
