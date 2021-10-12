package com.keita.filingcabinet.service;

import com.keita.filingcabinet.mockData.LogMockData;
import com.keita.filingcabinet.model.entity.Log;
import com.keita.filingcabinet.repository.LogRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

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

        logService.add(log.getFileId(), log.getOperationType());
    }

}
