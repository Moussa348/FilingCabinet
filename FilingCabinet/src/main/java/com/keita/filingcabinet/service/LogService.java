package com.keita.filingcabinet.service;

import com.keita.filingcabinet.model.entity.Log;
import com.keita.filingcabinet.model.enums.OperationType;
import com.keita.filingcabinet.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LogService {

    @Autowired
    private LogRepository logRepository;

    public void add(String name, String fileId, OperationType operationType) {
        logRepository.save(
                Log.builder()
                        .fileId(fileId)
                        .by(name)
                        .date(LocalDateTime.now())
                        .operationType(operationType)
                        .build());
    }

}
