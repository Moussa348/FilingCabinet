package com.keita.filingcabinet.repository;

import com.keita.filingcabinet.mockData.FileMockData;
import lombok.extern.java.Log;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@Log
@DataMongoTest
@ActiveProfiles("none")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FileRepositoryTest {
    @Autowired
    FileRepository fileRepository;

    @BeforeEach
    void init() {
        fileRepository.save(FileMockData.getFile());
    }

    @Test
    void shouldExistByFileName() {
        //ARRANGE
        String filename = "test.txt";
        String folderId = "61621ca50545544ead443f75";

        //ACT
        boolean exist = fileRepository.existsByFileNameAndFolderId(filename,folderId);

        //ASSERT
        assertTrue(exist);
    }

    @Test
    void shouldNotExistByFileName() {
        //ARRANGE
        String filename = "non_existent.txt";
        String folderId = "61621ca50545544ead443f75";

        //ACT
        boolean exist = fileRepository.existsByFileNameAndFolderId(filename,folderId);

        //ASSERT
        assertFalse(exist);
    }

}
