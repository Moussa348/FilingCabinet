package com.keita.filingcabinet.mapping;

import com.keita.filingcabinet.mockData.PatientMockData;
import com.keita.filingcabinet.model.dto.PatientCreation;
import com.keita.filingcabinet.model.dto.PatientFolder;
import com.keita.filingcabinet.model.entity.Patient;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PatientMapperTest {

    @Test
    void toEntity(){
        //ARRANGE
        PatientCreation patientCreation = PatientMockData.getPatientCreation();

        //ACT
        Patient patient = PatientMapper.toEntity(patientCreation);

        //ASSERT
        assertEquals(patientCreation.getFirstName(),patient.getFirstName());
        assertEquals(patientCreation.getLastName(),patient.getLastName());
        assertEquals(patientCreation.getAddress(),patient.getAddress());
        assertEquals(patientCreation.getEmail(),patient.getEmail());
        assertEquals(patientCreation.getAddress(),patient.getAddress());
        assertEquals(patientCreation.getPhoneNumber(),patient.getPhoneNumber());
        assertEquals(patientCreation.getSocialNumber(),patient.getSocialNumber());
    }

    @Test
    void toPatientFolder(){
        //ARRANGE
        Patient patient = PatientMockData.getListPatients().get(0);

        //ACT
        PatientFolder patientFolder = PatientMapper.toPatientFolder(patient);

        //ASSERT
        assertEquals(patient.getId(),patientFolder.getPatientId());
        assertEquals(patient.getFirstName() + " " + patient.getLastName(),patientFolder.getPatientName());
    }

}
