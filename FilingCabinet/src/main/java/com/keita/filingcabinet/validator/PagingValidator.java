package com.keita.filingcabinet.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PagingValidator implements ConstraintValidator<PagingConstraint,Integer> {
    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        return (integer != null) && (integer >= 0);
    }
}
