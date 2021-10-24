package com.keita.filingcabinet.mockData;

import com.keita.filingcabinet.model.entity.Log;
import com.keita.filingcabinet.model.enums.OperationType;
import com.keita.filingcabinet.model.enums.Role;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class LogMockData {

    public static Log getLog(){
        return Log.builder()
                .fileId("61627e75c97fd02ce1aaf592")
                .fileName("test.txt")
                .by(Collections.singletonMap(Role.USER.toString(), "employee1"))
                .date(LocalDateTime.now())
                .operationType(OperationType.UPLOAD)
                .build();
    }

    public static List<Log> getListLog(){
        return Arrays.asList(
                Log.builder()
                        .fileId("6174bbc818e35e6a8653f37f")
                        .fileName("test1.txt")
                        .by(Collections.singletonMap(Role.USER.toString(), "employee1"))
                        .date(LocalDateTime.now())
                        .operationType(OperationType.UPLOAD)
                        .build(),
                Log.builder()
                        .fileId("6174bbcd18023a98f36552cf")
                        .fileName("test2.txt")
                        .by(Collections.singletonMap(Role.USER.toString(), "employee1"))
                        .date(LocalDateTime.now())
                        .operationType(OperationType.UPLOAD)
                        .build(),
                Log.builder()
                        .fileId("6174bbd1bded40d744944d85")
                        .fileName("test3.txt")
                        .by(Collections.singletonMap(Role.USER.toString(), "employee1"))
                        .date(LocalDateTime.now())
                        .operationType(OperationType.UPLOAD)
                        .build()
        );
    }
}
