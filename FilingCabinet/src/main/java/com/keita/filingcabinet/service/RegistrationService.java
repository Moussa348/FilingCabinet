package com.keita.filingcabinet.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.keita.filingcabinet.model.entity.Folder;
import com.keita.filingcabinet.model.entity.Patient;
import com.keita.filingcabinet.model.entity.User;
import com.keita.filingcabinet.repository.FolderRepository;
import com.keita.filingcabinet.repository.PatientRepository;
import com.keita.filingcabinet.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Order(2)
public class RegistrationService implements CommandLineRunner {

    //TODO --> move to DbInit and reserve this class for form registration for user and patient

    private final UserRepository userRepository;

    private final PatientRepository patientRepository;

    private final FolderRepository folderRepository;

    private final ResourceLoader resourceLoader;

    public RegistrationService(UserRepository userRepository, PatientRepository patientRepository, FolderRepository folderRepository, ResourceLoader resourceLoader) {
        this.userRepository = userRepository;
        this.patientRepository = patientRepository;
        this.folderRepository = folderRepository;
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

        folderRepository.saveAll(patientRepository.saveAll(objectMapper.readValue(file,typeReference))
                .stream()
                .map(Patient::getId)
                .map((id) -> Folder.builder().patientId(id).categoryName("EVALUATION").build())
                .collect(Collectors.toList())
        );
    }

    @Override
    public void run(String... args) throws Exception {
        registerUsers();
        registerPatients();
    }
}
