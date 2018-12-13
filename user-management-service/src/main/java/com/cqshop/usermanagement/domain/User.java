package com.cqshop.usermanagement.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Mateusz Brycki on 01/10/2018.
 * how it should be created?
 * where it should be saved?
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class User {

    public enum Status {
        NEW, ACTIVATED, ARCHIVED;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long ID;

    private String username;
    private String password;
    private String email;

    @Setter
    @Builder.Default
    private Status status = Status.NEW;

}
