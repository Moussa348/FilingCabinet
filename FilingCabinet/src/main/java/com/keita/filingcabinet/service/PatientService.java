package com.keita.filingcabinet.service;

import com.keita.filingcabinet.mapping.PatientMapper;
import com.keita.filingcabinet.model.dto.PatientCreation;
import com.keita.filingcabinet.model.dto.PatientFolder;
import com.keita.filingcabinet.model.entity.Patient;
import com.keita.filingcabinet.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
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

        String id = patientRepository.save(PatientMapper.toEntity(patientCreation)).getId();

        categoryService.getListCategoryName().forEach(name -> folderService.createFolder(id, name));

        return null;
    }

    //TODO --> exist methode for formulaire

    public List<String> getListPatientId() {
        return patientRepository.findAll().stream()
                .map(Patient::getId)
                .collect(Collectors.toList());
    }

    public List<PatientFolder> getListPatientFolder() {
        return patientRepository.findAll().stream()
                .map(PatientMapper::toPatientFolder)
                .collect(Collectors.toList());
    }

}
