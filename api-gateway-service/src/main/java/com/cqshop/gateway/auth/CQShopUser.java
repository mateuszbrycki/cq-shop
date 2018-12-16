package com.cqshop.gateway.auth;

import com.cqshop.gateway.dto.UserAuthResponse;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mateusz Brycki on 16/12/2018.
 */
public class CQShopUser extends User {

    private final Long userId;

    public CQShopUser(UserAuthResponse response) {
        super(response.getUsername(), response.getPassword(), !response.getStatus().equals("ARCHIVED"), true, true, true, getAuthorities(response.getRoles()));
        this.userId = response.getUserId();
    }

    public Long getUserId() {
        return this.userId;
    }

    private static List<GrantedAuthority> getAuthorities (List<String> roles) {

        List<GrantedAuthority> authorities = new ArrayList<>();

        for(String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }

        return authorities;
    }
}
