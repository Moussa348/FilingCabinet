package com.keita.filingcabinet.service;

import com.keita.filingcabinet.mapping.PatientMapper;
import com.keita.filingcabinet.model.dto.PagingRequest;
import com.keita.filingcabinet.model.dto.PatientCreation;
import com.keita.filingcabinet.model.dto.PatientFolder;
import com.keita.filingcabinet.model.entity.Patient;
import com.keita.filingcabinet.repository.PatientRepository;
import com.keita.filingcabinet.security.OwnershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private FolderService folderService;

    public Map<String, Integer> createPatient(PatientCreation patientCreation) {
        AtomicInteger counter = new AtomicInteger(0);
        Patient patient = PatientMapper.toEntity(patientCreation);

        patient.setRegisterBy(OwnershipService.getCurrentUserDetails());

        String id = patientRepository.save(patient).getId();

        categoryService.getListCategoryName().forEach(name -> {
            folderService.createFolder(id, name);
            counter.getAndIncrement();
        });

        return Collections.singletonMap(id, counter.get());
    }

    //TODO --> exist methode for formulaire
    public List<String> getListPatientId() {
        return patientRepository.findAll().stream()
                .map(Patient::getId)
                .collect(Collectors.toList());
    }

    public List<PatientFolder> getListPatientFolder(PagingRequest pagingRequest) {
        return patientRepository.findAll(PageRequest.of(pagingRequest.getNoPage(),pagingRequest.getSize())).stream()
                .map(patient -> {
                    PatientFolder patientFolder = PatientMapper.toPatientFolder(patient);

                    patientFolder.setMapFolders(folderService.getMapFolderByPatientId(patient.getId()));

                    return patientFolder;
                })
                .collect(Collectors.toList());
    }

}
