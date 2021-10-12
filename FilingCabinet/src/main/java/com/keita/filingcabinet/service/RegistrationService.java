package com.keita.filingcabinet.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.keita.filingcabinet.model.entity.User;
import com.keita.filingcabinet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@Order(2)
public class RegistrationService implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ResourceLoader resourceLoader;

    private void register() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<User>> typeReference = new TypeReference<List<User>>() {};

        File file = resourceLoader.getResource("classpath:static/users.json").getFile();

        userRepository.saveAll(objectMapper.readValue(file,typeReference));
    }

    @Override
    public void run(String... args) throws Exception {
        register();
    }
}
