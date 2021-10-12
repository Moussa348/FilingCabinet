package com.keita.filingcabinet.service;

import com.keita.filingcabinet.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class PatientServiceTest {

    @Mock
    PatientRepository patientRepository;

    @Mock
    CategoryService categoryService;

    @InjectMocks
    FolderService folderService;

    @Test
    void shouldCreatePatient(){
        //ARRANGE

        //ACT

        //ASSERT

    }

    @Test
    void shouldNotCreatePatient(){
        //ARRANGE

        //ACT

        //ASSERT

    }

    @Test
    void shouldGetListPatientId(){
        //ARRANGE

        //ACT

        //ASSERT

    }

    @Test
    void shouldGetListPatientFolder(){
        //ARRANGE

        //ACT

        //ASSERT

    }

}
