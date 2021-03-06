package com.cqshop.usermanagement.domain.repository;

import com.cqshop.usermanagement.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Mateusz Brycki on 01/10/2018.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByEmail(String email);
    User findByUsername(String username);
}
