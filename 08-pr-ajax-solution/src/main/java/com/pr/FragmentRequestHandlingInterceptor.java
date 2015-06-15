package com.pr;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by iuliana.cosmina on 6/15/15.
 */

/**
 * A Spring MVC interceptor that examines the request URL for the presence of a
 * request parameter called "fragment". If one is found its value is appended to the
 * logical view name. For example given a request parameter fragments=body and a
 * logical view name of "accounts/newUpdate" results in "accounts/newUpdate.body" .</p>
 */
public class FragmentRequestHandlingInterceptor extends HandlerInterceptorAdapter {

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String fragment = ServletRequestUtils.getStringParameter(request, "fragment");
        if (!StringUtils.hasText(fragment)) {
            return;
        }
        String name = modelAndView.getViewName();
        modelAndView.setViewName(name + "." + fragment);
    }

}
