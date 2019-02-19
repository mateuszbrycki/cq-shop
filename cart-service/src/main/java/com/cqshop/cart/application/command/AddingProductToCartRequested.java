package com.cqshop.cart.application.command;

import com.cqshop.cqrs.common.command.AbstractApplicationCommandWithTimestamp;
import com.cqshop.cqrs.common.command.ApplicationCommandAnnotation;
import lombok.*;

import java.io.Serializable;

/**
 * Created by Mateusz Brycki on 27/12/2018.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@ApplicationCommandAnnotation
@Builder
public class AddingProductToCartRequested extends AbstractApplicationCommandWithTimestamp implements Serializable {

    @NonNull
    private long productId;

    @NonNull
    private int quantity;

    @NonNull
    private long userId;

}
