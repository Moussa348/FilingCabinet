package com.keita.filingcabinet.validator.patient;

import com.keita.filingcabinet.exception.PatientAlreadyExistException;
import com.keita.filingcabinet.repository.PatientRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PatientExistValidator implements ConstraintValidator<PatientExistConstraint,String> {

    @Autowired
    private PatientRepository patientRepository;
    @SneakyThrows
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(patientRepository.existsByEmail(s))
            return true;

        throw new PatientAlreadyExistException("This Patient already exist!");
    }
}
