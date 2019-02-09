package com.cqshop.gateway.interceptor;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.cloud.netflix.zuul.filters.pre.PreDecorationFilter;
import org.springframework.stereotype.Component;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.REQUEST_URI_KEY;

/**
 * Created by Mateusz Brycki on 2019-02-09.
 */
@Component
public class ApiRewriteZuulFilter extends ZuulFilter
{
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
        RequestContext context = RequestContext.getCurrentContext();
        Object originalRequestPath = context.get(REQUEST_URI_KEY);
        String modifiedRequestPath = "/api/" + originalRequestPath;
        context.put(REQUEST_URI_KEY, modifiedRequestPath);

        return null;
    }
}