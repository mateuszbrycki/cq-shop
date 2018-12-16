package com.cqshop.usermanagement.bootstrap;

import com.cqshop.usermanagement.domain.UserRole;
import com.cqshop.usermanagement.domain.repository.UserRoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by Mateusz Brycki on 16/12/2018.
 */
@Component
@AllArgsConstructor
public class DatabaseBootstrap {

    private final UserRoleRepository userRoleRepository;


    @PostConstruct
    public void onApplicationEvent() {
        initUserRoles();
    }

    private void initUserRoles() {
        userRoleRepository.save(UserRole.builder().role("ROLE_ADMIN").build());
        userRoleRepository.save(UserRole.builder().role("ROLE_USER").build());
    }
}