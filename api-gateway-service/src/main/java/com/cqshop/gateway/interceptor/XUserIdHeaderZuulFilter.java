package com.cqshop.gateway.interceptor;

import com.cqshop.gateway.auth.CQShopUser;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.pre.PreDecorationFilter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Created by Mateusz Brycki on 2019-02-09.
 */
@Slf4j
@Component
public class XUserIdHeaderZuulFilter extends ZuulFilter
{
    private static final String X_USER_HEADER = "X-User-Id";

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return PreDecorationFilter.FILTER_ORDER + 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            return null;
        }

        Object principal = authentication.getPrincipal();

        if (principal instanceof CQShopUser) {
            CQShopUser user = (CQShopUser)principal;
            RequestContext context = RequestContext.getCurrentContext();

            context.addZuulRequestHeader(X_USER_HEADER, user.getUserId().toString());

            log.info("Attaching " + X_USER_HEADER + ": " + user.getUserId());
        }

        return null;
    }
}