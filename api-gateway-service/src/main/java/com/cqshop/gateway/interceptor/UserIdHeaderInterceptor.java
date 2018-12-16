package com.cqshop.gateway.interceptor;

import com.cqshop.gateway.auth.CQShopUser;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Created by Mateusz Brycki on 16/12/2018.
 */
@Slf4j
@Component
public class UserIdHeaderInterceptor implements RequestInterceptor {

    private static final String X_USER_HEADER = "X-User-Id";

    @Override
    public void apply(RequestTemplate template) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            return;
        }

        Object principal = authentication.getPrincipal();

        if (principal instanceof CQShopUser) {
            CQShopUser user = (CQShopUser)principal;
            template.header(X_USER_HEADER, String.valueOf(user.getUserId()));

            log.info("Attaching " + X_USER_HEADER + ": " + user.getUserId());
        }
    }
}
