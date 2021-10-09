package com.keita.filingcabinet.validator.folder;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.METHOD, ElementType.FIELD,ElementType.PARAMETER })
@Constraint(validatedBy = FolderExistValidator.class)
public @interface FolderExistConstraint {
    String message() default "This folder do not exist!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
