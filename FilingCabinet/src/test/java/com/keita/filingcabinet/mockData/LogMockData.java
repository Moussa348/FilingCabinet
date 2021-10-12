package com.keita.filingcabinet.mockData;

import com.keita.filingcabinet.model.entity.Log;
import com.keita.filingcabinet.model.enums.OperationType;
import com.keita.filingcabinet.model.enums.Role;

import java.time.LocalDateTime;
import java.util.Collections;

public abstract class LogMockData {

    public static Log getLog(){
        return Log.builder()
                .fileId("61627e75c97fd02ce1aaf592")
                .by(Collections.singletonMap("employee1", Role.USER))
                .date(LocalDateTime.now())
                .operationType(OperationType.UPLOAD)
                .build();
    }
}
