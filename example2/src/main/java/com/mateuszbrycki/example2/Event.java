package com.mateuszbrycki.example2;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by Mateusz Brycki on 11/09/2018.
 */

@Getter
@Setter
@Builder
@ToString
public class Event {

    private Long timestamp;
    private String message;

}
