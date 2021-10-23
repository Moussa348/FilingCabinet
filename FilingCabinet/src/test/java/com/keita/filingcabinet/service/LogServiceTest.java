package com.keita.filingcabinet.service;

import com.keita.filingcabinet.mockData.LogMockData;
import com.keita.filingcabinet.mockData.OwnershipMockData;
import com.keita.filingcabinet.mockData.UserMockData;
import com.keita.filingcabinet.model.entity.Log;
import com.keita.filingcabinet.repository.LogRepository;
import com.keita.filingcabinet.security.OwnershipService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.test.context.support.WithSecurityContext;

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
        logService.add(log.getOnFile(), log.getOperationType());
    }

}
