package com.cqshop.warehouse.application.command;

import com.cqshop.cqrs.common.command.AbstractApplicationCommand;
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
public class MaterializeUserReservations extends AbstractApplicationCommand implements Serializable {

    @NotNull
    private Long userId;

}
