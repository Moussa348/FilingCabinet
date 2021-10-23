package com.keita.filingcabinet.service;

import com.keita.filingcabinet.mockData.CategoryMockData;
import com.keita.filingcabinet.mockData.FileMockData;
import com.keita.filingcabinet.mockData.FolderMockData;
import com.keita.filingcabinet.mockData.PatientMockData;
import com.keita.filingcabinet.model.dto.PagingRequest;
import com.keita.filingcabinet.model.dto.PatientCreation;
import com.keita.filingcabinet.model.dto.PatientFolder;
import com.keita.filingcabinet.model.entity.Category;
import com.keita.filingcabinet.model.entity.Patient;
import com.keita.filingcabinet.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class PatientServiceTest {

    @Mock
    PatientRepository patientRepository;

    @Mock
    CategoryService categoryService;

    @Mock
    FolderService folderService;

    @InjectMocks
    PatientService patientService;

    @Test
    void shouldCreatePatient(){
        //ARRANGE
        PatientCreation patientCreation = PatientMockData.getPatientCreation();
        Patient patient = PatientMockData.getListPatients().get(0);

        when(patientRepository.save(any())).thenReturn(patient);
        when(categoryService.getListCategoryName()).thenReturn(CategoryMockData.getListCategoryForRepoTest().stream()
                .map(Category::getName)
                .collect(Collectors.toList()));
        when(folderService.createFolder(any(),any())).thenReturn("616614a5c650ebeecd24b0b5");

        //ACT
        Map<String,Integer> mapCountFolderForPatient = patientService.createPatient(patientCreation);

        //ASSERT
        assertEquals(2,mapCountFolderForPatient.get(patient.getId()));
    }

    @Test
    void shouldGetListPatientId(){
        //ARRANGE
        when(patientRepository.findAll()).thenReturn(PatientMockData.getListPatients());

        //ACT
        List<String> patientIds = patientService.getListPatientId();

        //ASSERT
        assertEquals(1,patientIds.size());
    }

    @Test
    void shouldGetListPatientFolder(){
        //ARRANGE
        PagingRequest pagingRequest = FileMockData.getPagingRequest();
        when(patientRepository.findAll(PageRequest.of(pagingRequest.getNoPage(),pagingRequest.getSize()))).thenReturn(new PageImpl<>(PatientMockData.getListPatients()));
        when(folderService.getMapFolderByPatientId(any())).thenReturn(FolderMockData.getMapFolders());

        //ACT
        List<PatientFolder> patientFolders = patientService.getListPatientFolder(pagingRequest);

        //ASSERT
        assertEquals(1,patientFolders.size());
        patientFolders.forEach( patientFolder -> assertEquals(3,patientFolder.getMapFolders().size()));
    }

}
