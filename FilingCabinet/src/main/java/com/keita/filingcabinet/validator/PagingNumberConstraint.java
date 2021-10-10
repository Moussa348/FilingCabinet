package com.keita.filingcabinet.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.METHOD, ElementType.FIELD,ElementType.PARAMETER })
@Constraint(validatedBy = PagingNumberValidator.class)
public @interface PagingNumberConstraint {
    String message() default "Invalid Paging request";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
