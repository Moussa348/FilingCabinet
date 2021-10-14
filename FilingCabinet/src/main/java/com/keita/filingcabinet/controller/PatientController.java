package com.keita.filingcabinet.controller;

import com.keita.filingcabinet.model.dto.PatientCreation;
import com.keita.filingcabinet.model.dto.PatientFolder;
import com.keita.filingcabinet.service.PatientService;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAnyAuthority('SUDO','USER')")
    public Map<String, Integer> createPatient(@Valid @RequestBody PatientCreation patientCreation) {
        return patientService.createPatient(patientCreation);
    }

    @GetMapping("/getListPatientFolder")
    @PreAuthorize("hasAnyAuthority('SUDO','USER')")
    public List<PatientFolder> getListPatientFolder() {
        return patientService.getListPatientFolder();
    }

}
