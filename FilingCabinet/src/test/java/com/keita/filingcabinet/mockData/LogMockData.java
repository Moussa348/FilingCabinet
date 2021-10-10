package com.keita.filingcabinet.mockData;

import com.keita.filingcabinet.model.entity.Log;
import com.keita.filingcabinet.model.enums.OperationType;

import java.time.LocalDateTime;

public abstract class LogMockData {

    public static Log getLog(){
        return Log.builder()
                .fileId("61627e75c97fd02ce1aaf592")
                .by("employee1")
                .date(LocalDateTime.now())
                .operationType(OperationType.READ)
                .build();
    }
}
