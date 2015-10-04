package com.pr.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by iuliana.cosmina on 5/6/15.
 */
@SuppressWarnings("unchecked")
@Component
public class AuditInterceptor extends HandlerInterceptorAdapter {
    private Logger logger = LoggerFactory.getLogger(AuditInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod hm = (HandlerMethod) handler;
            logger.info(hm.getBeanType() + "." + hm.getMethod().getName() + "[PREHANDLE]");

            long startTime = System.currentTimeMillis();
            request.setAttribute(hm.getBeanType() + "." + hm.getMethod().getName() + "startTime", startTime);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod hm = (HandlerMethod) handler;
            logger.info(hm.getBeanType() + "." + hm.getMethod().getName() + "[POSTHANDLE]");

            long startTime = (Long) request.getAttribute(hm.getBeanType() + "." + hm.getMethod().getName() + "startTime");
            long endTime = System.currentTimeMillis();
            long executeTime = endTime - startTime;
            logger.info("postHandler: "+hm.getMethod().getName()+" execution time = " + executeTime);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod hm = (HandlerMethod) handler;
            logger.info(hm.getBeanType() + "." + hm.getMethod().getName() + "[AFTERCOMPLETION]");
        }
    }
}
