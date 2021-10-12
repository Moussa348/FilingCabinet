package com.keita.filingcabinet.repository;

import com.keita.filingcabinet.mockData.FolderMockData;
import com.keita.filingcabinet.model.entity.Folder;
import lombok.extern.java.Log;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Log
@DataMongoTest
@ActiveProfiles("none")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FolderRepositoryTest {

    @Autowired
    FolderRepository folderRepository;

    @BeforeEach
    void init(){
        folderRepository.saveAll(FolderMockData.getListFolder());
    }

    @Test
    void findAllByPatientId(){
        //ARRANGE
        String id = "6165d33784875473c6d85b66";

        //ACT
        List<Folder> folders = folderRepository.findAllByPatientId(id);

        //ASSERT
        assertEquals(2,folders.size());
    }

}
