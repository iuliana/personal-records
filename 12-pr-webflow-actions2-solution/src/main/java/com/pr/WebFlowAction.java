package com.pr;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * Created by iuliana.cosmina on 8/31/15.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface WebFlowAction {

    /**
     * The value may indicate a suggestion for a logical component name,
     * to be turned into a Spring bean in case of an autodetected component.
     * @return the suggested component name, if any
     */
    String value() default "";

}