package com.pr.init;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by iuliana.cosmina on 10/4/15.
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.pr.*"})
@EnableConfigurationProperties(AppSettings.class)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}