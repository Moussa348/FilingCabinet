package com.keita.filingcabinet.model.dto;

import com.keita.filingcabinet.validator.patient.PatientExistConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientCreation implements Serializable {

    @PatientExistConstraint
    private String email;
    private String firstName, lastName, phoneNumber, address, socialNumber;
}
