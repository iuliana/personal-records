package com.pr.util;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Iuliana Cosmina
 */
public class PncValidator implements ConstraintValidator<Pnc, String> {

    @Override
    public void initialize(Pnc constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return (value == null) || value.matches("[1-2][0-9]*");
    }

}
