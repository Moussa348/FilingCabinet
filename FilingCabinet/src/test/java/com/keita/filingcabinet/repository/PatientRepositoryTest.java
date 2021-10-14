package com.keita.filingcabinet.repository;

import com.keita.filingcabinet.mockData.PatientMockData;
import lombok.extern.java.Log;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ActiveProfiles;

import static com.mongodb.assertions.Assertions.assertFalse;
import static com.mongodb.assertions.Assertions.assertTrue;

@Log
@DataMongoTest
@ActiveProfiles("none")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PatientRepositoryTest {

    @Autowired
    PatientRepository patientRepository;

    @BeforeEach
    void init() {
        patientRepository.saveAll(PatientMockData.getListPatients());
    }

    @Test
    void shouldExistByEmail(){
        //ARRANGE
        String email = "mathieu@gmail.com";

        //ACT
        boolean exist = patientRepository.existsByEmail(email);

        //ASSERT
        assertTrue(exist);
    }

    @Test
    void shouldNotExistByEmail(){
        //ARRANGE
        String email = "non_existent";

        //ACT
        boolean exist = patientRepository.existsByEmail(email);

        //ASSERT
        assertFalse(exist);
    }

}
