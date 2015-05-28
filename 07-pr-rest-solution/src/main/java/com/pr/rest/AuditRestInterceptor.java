package com.pr.rest;

import com.fasterxml.jackson.annotation.JsonView;
import com.pr.ents.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.JsonViewResponseBodyAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * Created by iuliana.cosmina on 5/23/15.
 */
@ControllerAdvice(basePackages = "com.pr.rest")
public class AuditRestInterceptor extends JsonViewResponseBodyAdvice {
    private Logger logger = LoggerFactory.getLogger(AuditRestInterceptor.class);

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        logger.info("-----> Audit REST interceptor supports() ? " + Person.class.isAssignableFrom(returnType.getParameterType()));
        return (super.supports(returnType, converterType) && returnType.getMethodAnnotation(JsonView.class) != null);
    }

    @Override
    protected void beforeBodyWriteInternal(MappingJacksonValue bodyContainer, MediaType contentType, MethodParameter returnType, ServerHttpRequest request, ServerHttpResponse response) {
        logger.info("-----> Audit REST interceptor beforeBodyWrite()");
        super.beforeBodyWriteInternal(bodyContainer, contentType, returnType, request, response);
        response.getHeaders().add(HttpHeaders.ACCEPT_CHARSET, "UTF-8");
    }
}
