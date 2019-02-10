package com.cqshop.usermanagement.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
@Table(name="user_account")
@EqualsAndHashCode
public class User {

    public enum Status {
        NEW, ACTIVATED, ARCHIVED;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long userId;

    private String username;
    private String password;
    private String email;

    @Setter
    @Builder.Default
    private Status status = Status.NEW;

    @NotNull
    @OneToOne
    @JoinColumn(name="fk_role_id")
    @JsonIgnore
    private UserRole role;

}
