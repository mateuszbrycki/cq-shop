package com.cqshop.usermanagement.domain.repository;

import com.cqshop.usermanagement.domain.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {

    UserRole findByRole(String role);

}
