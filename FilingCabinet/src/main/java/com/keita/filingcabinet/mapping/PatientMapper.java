package com.keita.filingcabinet.mapping;

import com.keita.filingcabinet.model.dto.PatientCreation;
import com.keita.filingcabinet.model.dto.PatientFolder;
import com.keita.filingcabinet.model.entity.Patient;

public abstract class PatientMapper {

    public static Patient toEntity(PatientCreation patientCreation) {
        return Patient.builder()
                .firstName(patientCreation.getFirstName())
                .lastName(patientCreation.getLastName())
                .email(patientCreation.getEmail())
                .phoneNumber(patientCreation.getPhoneNumber())
                .address(patientCreation.getAddress())
                .socialNumber(patientCreation.getSocialNumber())
                .build();
    }

    public static PatientFolder toPatientFolder(Patient patient){
        return PatientFolder.builder()
                .patientId(patient.getId())
                .patientName(patient.getFirstName() + " " + patient.getLastName())
                .build();
    }
}
