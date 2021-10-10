package com.keita.filingcabinet.validator.folder;

import com.keita.filingcabinet.exception.FolderNotFoundException;
import com.keita.filingcabinet.repository.FolderRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Valid;

public class FolderExistValidator implements ConstraintValidator<FolderExistConstraint,String> {

    @Autowired
    private FolderRepository folderRepository;

    @Override
    @SneakyThrows
    public boolean isValid(@Valid String id, ConstraintValidatorContext constraintValidatorContext) {
        if(folderRepository.existsById(id))
            return true;
        throw new FolderNotFoundException("NO FOLDER FOUND WITH THIS ID");
    }
}
