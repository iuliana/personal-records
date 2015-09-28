package com.pr.init;

/**
 * Created by iuliana.cosmina on 9/27/15.
 */

import com.pr.WebFlowAction;
import com.pr.config.MvcConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@SpringBootApplication
@ComponentScan(basePackages = {"com.pr.*"},
        includeFilters = @ComponentScan.Filter(
                value = WebFlowAction.class,
                type = FilterType.ANNOTATION
        ))
@ImportResource({"classpath:spring/app-service-config.xml", "classpath:spring/db-config.xml"})
@EnableConfigurationProperties(AppSettings.class)
public class Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String[] args) {
        //SpringApplication.run(Application.class, args);
        new SpringApplicationBuilder(Application.class).run(args);
    }
}
