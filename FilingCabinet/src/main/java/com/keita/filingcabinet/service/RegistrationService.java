package com.keita.filingcabinet.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.keita.filingcabinet.model.entity.Patient;
import com.keita.filingcabinet.model.entity.User;
import com.keita.filingcabinet.repository.PatientRepository;
import com.keita.filingcabinet.repository.UserRepository;
import com.sun.jna.platform.win32.OaIdl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import javax.swing.text.DateFormatter;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.List;

@Service
@Order(2)
public class RegistrationService implements CommandLineRunner {

    private final UserRepository userRepository;

    private final PatientRepository patientRepository;

    private final ResourceLoader resourceLoader;

    public RegistrationService(UserRepository userRepository, PatientRepository patientRepository, ResourceLoader resourceLoader) {
        this.userRepository = userRepository;
        this.patientRepository = patientRepository;
        this.resourceLoader = resourceLoader;
    }

    private void registerUsers() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<User>> typeReference = new TypeReference<List<User>>() {};
        File file = resourceLoader.getResource("classpath:static/users.json").getFile();

        userRepository.saveAll(objectMapper.readValue(file, typeReference));
    }

    private void registerPatients() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<Patient>> typeReference = new TypeReference<List<Patient>>() {};
        File file = resourceLoader.getResource("classpath:static/patients.json").getFile();

        patientRepository.saveAll(objectMapper.readValue(file,typeReference));
    }

    @Override
    public void run(String... args) throws Exception {
        registerUsers();
        registerPatients();
    }
}
