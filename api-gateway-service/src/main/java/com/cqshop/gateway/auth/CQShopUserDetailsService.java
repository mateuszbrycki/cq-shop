package com.cqshop.gateway.auth;

import com.cqshop.gateway.dto.UserAuthResponse;
import com.cqshop.gateway.repository.UserManagementRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Created by Mateusz Brycki on 16/12/2018.
 */
@AllArgsConstructor
@Component
public class CQShopUserDetailsService implements UserDetailsService {

    private final UserManagementRepository userManagementRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        ResponseEntity<UserAuthResponse> response = userManagementRepository.findByUsername(username);
        if (response.getStatusCode().is4xxClientError()) {
            throw new UsernameNotFoundException(
                    "No user found with username: "+ username);
        }

        UserAuthResponse user = response.getBody();
        return new CQShopUser(user);
    }


}
