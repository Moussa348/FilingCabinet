package com.keita.filingcabinet.service;

import com.keita.filingcabinet.mockData.FileMockData;
import com.keita.filingcabinet.mockData.LogMockData;
import com.keita.filingcabinet.model.dto.PagingRequest;
import com.keita.filingcabinet.model.entity.Log;
import com.keita.filingcabinet.repository.LogRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LogServiceTest {

    @Mock
    LogRepository logRepository;

    @InjectMocks
    LogService logService;

    @Test
    void add() {
        //ARRANGE
        Log log = LogMockData.getLog();
        when(logRepository.save(any(Log.class))).thenReturn(log);

        //ACT
        logService.add(log.getFileId(), log.getFileName(), log.getOperationType());

    }

    @Test
    void shouldGetListLogByFileId() {
        //ARRANGE
        String fileId = "6174bbc818e35e6a8653f37f";
        PagingRequest pagingRequest = FileMockData.getPagingRequest();

        when(logRepository.findAllByFileId(anyString(), any())).thenReturn(LogMockData.getListLog());

        //ACT
        List<Log> logs = logService.getListLogByFileId(fileId, pagingRequest);

        //ASSERT
        assertEquals(3, logs.size());
    }

}
