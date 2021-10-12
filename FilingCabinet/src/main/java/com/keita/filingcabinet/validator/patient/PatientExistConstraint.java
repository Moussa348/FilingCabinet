package com.keita.filingcabinet.validator.patient;

import com.keita.filingcabinet.validator.PagingNumberValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.METHOD, ElementType.FIELD,ElementType.PARAMETER })
@Constraint(validatedBy = PagingNumberValidator.class)
public @interface PatientExistConstraint {
    String message() default "Patient already exist with this email";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
