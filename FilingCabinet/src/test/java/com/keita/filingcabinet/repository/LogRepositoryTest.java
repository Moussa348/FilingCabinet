package com.keita.filingcabinet.repository;

import com.keita.filingcabinet.mockData.LogMockData;
import com.keita.filingcabinet.model.entity.Log;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataMongoTest
@ActiveProfiles("none")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LogRepositoryTest {

    @Autowired
    private LogRepository logRepository;

    @BeforeEach
    void init() {
        logRepository.saveAll(LogMockData.getListLog());
    }

    @Test
    void shouldFindAllByFileId() {
        //ARRANGE
        String id = "6174bbc818e35e6a8653f37f";

        //ACT
        List<Log> logs = logRepository.findAllByFileId(id, PageRequest.of(0, 10, Sort.by("date")));

        //ASSERT
        assertEquals(1, logs.size());
    }

}
