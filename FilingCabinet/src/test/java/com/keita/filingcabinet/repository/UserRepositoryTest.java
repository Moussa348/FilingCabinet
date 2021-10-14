package com.keita.filingcabinet.repository;

import com.keita.filingcabinet.mockData.UserMockData;
import com.keita.filingcabinet.model.entity.User;
import lombok.extern.java.Log;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Log
@DataMongoTest
@ActiveProfiles("none")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    void init(){userRepository.saveAll(UserMockData.getUsers());}

    @Test
    void shouldFindFindByEmailAndPasswordAndActiveTrueAndAccountVerifiedTrue(){
        //ARRANGE
        String email = "employee1@gmail.com";
        String password = "employee";

        //ACT
        Optional<User> user = userRepository.findByEmailAndPasswordAndIsActiveTrueAndIsAccountVerifiedTrue(email,password);

        //ASSERT
        assertTrue(user.isPresent());
    }

    @Test
    void shouldNotFindFindByEmailAndPasswordAndActiveTrueAndAccountVerifiedTrue(){
        //ARRANGE
        String email = "employee3@gmail.com";
        String password = "employee";

        //ACT
        Optional<User> user = userRepository.findByEmailAndPasswordAndIsActiveTrueAndIsAccountVerifiedTrue(email,password);

        //ASSERT
        assertTrue(user.isEmpty());
    }

}
