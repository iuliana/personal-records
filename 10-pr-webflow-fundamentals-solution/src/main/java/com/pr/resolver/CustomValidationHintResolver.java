package com.pr.resolver;

import org.springframework.stereotype.Component;
import org.springframework.webflow.validation.BeanValidationHintResolver;

/**
 * Created by iuliana.cosmina on 8/16/15.
 */
@Component("personValidationHintResolver")
public class CustomValidationHintResolver extends BeanValidationHintResolver {

}
