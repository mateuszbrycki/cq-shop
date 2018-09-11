package com.mateuszbrycki.example1;

import lombok.*;

/**
 * Created by Mateusz Brycki on 11/09/2018.
 */

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Event {

    private Long timestamp;
    private String message;

}
