package com.cqshop.usermanagement.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
@Entity
public class User {

    public enum Status {
        ACTIVATED, ARCHIVED;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long ID;

    private String username;
    private String password;
    private String email;

    @Builder.Default
    private Status status = Status.ACTIVATED;

}
