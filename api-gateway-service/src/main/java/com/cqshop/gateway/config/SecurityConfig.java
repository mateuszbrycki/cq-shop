package com.cqshop.gateway.config;

import com.cqshop.gateway.auth.CQShopUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by Mateusz Brycki on 16/12/2018.
 */
@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CQShopUserDetailsService cqShopUserDetailsService;

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(cqShopUserDetailsService);

    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().disable()
                .httpBasic()
                .and()
                    .authorizeRequests()
                        .antMatchers("/admin/**").hasRole("ADMIN")
                        .antMatchers("/login").permitAll()
                        .antMatchers(HttpMethod.POST, "/user").permitAll()
                        .antMatchers(HttpMethod.GET, "/user/**/activation/**").permitAll()
                        .antMatchers(HttpMethod.GET, "/user").authenticated()
                        .antMatchers(HttpMethod.PUT, "/user").authenticated()
                        .antMatchers(HttpMethod.DELETE, "/user").authenticated()
                        .antMatchers("/h2-console/**").permitAll()
                        .antMatchers("/css/**", "/js/**", "/img/**").permitAll()
                        .anyRequest().authenticated()
                .and()
                    .logout()
                    .logoutUrl("/logout")
                    .deleteCookies("JSESSIONID")
        ;
    }
}
