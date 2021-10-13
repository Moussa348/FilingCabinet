package com.keita.filingcabinet.controller;

import com.keita.filingcabinet.model.dto.PatientCreation;
import com.keita.filingcabinet.model.dto.PatientFolder;
import com.keita.filingcabinet.service.PatientService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/patient")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping("/createPatient")
    public Map<String, Integer> createPatient(@Valid @RequestBody PatientCreation patientCreation) {
        return patientService.createPatient(patientCreation);
    }

    @GetMapping("/getListPatientFolder")
    public List<PatientFolder> getListPatientFolder() {
        return patientService.getListPatientFolder();
    }

}
