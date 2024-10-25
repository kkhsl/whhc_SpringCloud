package com.springcloud.user.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Spring Been
 */
@Component
public class LogFilter extends ZuulFilter {


    /**
     * 在路由时执行
     * @return
     */
    @Override
    public String filterType() {
        return FilterConstants.ROUTE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER;
    }


    /**
     * 是否使用过滤器 true:表示使用过滤器 false：表示不使用过滤器
     * @return
     */
    @Override
    public boolean shouldFilter() {

//        RequestContext currentContext = RequestContext.getCurrentContext();
//        HttpServletRequest request = currentContext.getRequest();
//        if("true".equals(request.getParameter(DEBUG_PARAMETER.get()))){
//            return true;
//        }
//        return ROUTING_DEBUG.get();
        return true;
    }

    /**
     * 在路由时执行run方法
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        String remoteAddr = request.getServerName();
        System.out.println("访问地址：" + remoteAddr + request.getRequestURI());

//        int i = 10 / 0;

        // 目前返回值无意义，返回null即可
        return null;
    }
}
