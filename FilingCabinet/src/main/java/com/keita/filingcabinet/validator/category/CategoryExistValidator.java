package com.keita.filingcabinet.validator.category;

import com.keita.filingcabinet.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CategoryExistValidator implements ConstraintValidator<CategoryExistConstraint,String> {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        return categoryRepository.existsByName(name);
    }
}
