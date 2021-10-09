package com.keita.filingcabinet.validator.folder;

import com.keita.filingcabinet.repository.FolderRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FolderExistValidator implements ConstraintValidator<FolderExistConstraint,String> {

    @Autowired
    private FolderRepository folderRepository;

    @Override
    public boolean isValid(String id, ConstraintValidatorContext constraintValidatorContext) {
        return folderRepository.existsById(id);
    }
}
