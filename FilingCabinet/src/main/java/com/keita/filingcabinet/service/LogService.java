package com.keita.filingcabinet.service;

import com.keita.filingcabinet.model.entity.Log;
import com.keita.filingcabinet.model.enums.OperationType;
import com.keita.filingcabinet.repository.LogRepository;
import com.keita.filingcabinet.security.OwnershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class LogService {

    @Autowired
    private LogRepository logRepository;

    public void add(Map<String,String> onFile, OperationType operationType) {
        logRepository.save(
                Log.builder()
                        .onFile(onFile)
                        .by(OwnershipService.getCurrentUserDetails())
                        .date(LocalDateTime.now())
                        .operationType(operationType)
                        .build());
    }

}
