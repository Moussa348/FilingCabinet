package com.keita.filingcabinet.mockData;

import com.keita.filingcabinet.model.dto.PatientCreation;
import com.keita.filingcabinet.model.entity.Patient;

import java.util.Arrays;
import java.util.List;

public abstract class PatientMockData {

    public static PatientCreation getPatientCreation(){
        return PatientCreation.builder()
                .firstName("marc")
                .lastName("charbonneau")
                .email("marc@gmail.com")
                .phoneNumber("5149087654")
                .address("1324 rue Jean")
                .socialNumber("123133423")
                .build();
    }

    public static List<Patient> getListPatients(){
        return Arrays.asList(
                Patient.builder()
                        .id("616614a5c650ebeecd24b0b5")
                        .firstName("marc")
                        .lastName("charbonneau")
                        .email("marc@gmail.com")
                        .phoneNumber("5149087654")
                        .address("1324 rue Jean")
                        .socialNumber("123133423")
                        .build()
        );
    }
}
