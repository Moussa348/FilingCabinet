package com.keita.filingcabinet.validator;

import com.keita.filingcabinet.exception.InvalidPageRequestException;
import lombok.SneakyThrows;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PagingValidator implements ConstraintValidator<PagingConstraint,Integer> {

    @Override
    @SneakyThrows
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        if((integer != null) && (integer >= 0))
            return true;
        throw new InvalidPageRequestException("INVALID PAGE REQUEST!");
    }

}
