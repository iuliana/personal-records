
package com.pr.init;

import com.pr.config.MvcConfig;
import com.pr.config.SecurityConfig;
import com.pr.config.WebFlowConfig;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;


/**
 * Created by iuliana.cosmina on 9/27/15.
 */
@Configuration
public class WebConfig {

    @Bean
    public FilterRegistrationBean charEncodingFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        registrationBean.setFilter(characterEncodingFilter);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean httpMethodFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        HiddenHttpMethodFilter httpMethodFilter = new HiddenHttpMethodFilter();
        registrationBean.setFilter(httpMethodFilter);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean securityFilterChain() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        String filterName = AbstractSecurityWebApplicationInitializer.DEFAULT_FILTER_NAME;
        DelegatingFilterProxy springSecurityFilterChain = new DelegatingFilterProxy(
                filterName);
        registrationBean.setFilter(springSecurityFilterChain);
        return registrationBean;
    }


}

