package com.pr.util;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by iuliana.cosmina on 5/15/15.
 */
public class PncValidator implements ConstraintValidator<Pnc, String> {

    @Override
    public void initialize(Pnc constraintAnnotation) {
        // nothing to initialize
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
         return (value == null) || value.matches("[1-2][0-9]*");
    }
}
