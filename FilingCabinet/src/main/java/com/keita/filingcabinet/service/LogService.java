package com.keita.filingcabinet.service;

import com.keita.filingcabinet.model.entity.Log;
import com.keita.filingcabinet.model.enums.OperationType;
import com.keita.filingcabinet.model.enums.Role;
import com.keita.filingcabinet.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;

@Service
public class LogService {

    @Autowired
    private LogRepository logRepository;

    public void add(String fileId, OperationType operationType) {
        logRepository.save(
                Log.builder()
                        .fileId(fileId)
                        .by(Collections.singletonMap("", Role.USER))//TODO --> getLoggedUser
                        .date(LocalDateTime.now())
                        .operationType(operationType)
                        .build());
    }

}
