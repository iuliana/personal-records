package com.pr.util;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Iuliana Cosmina
 */
@Constraint(validatedBy = PncValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Pnc {

    String message() default "{pncFormatExpected}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
