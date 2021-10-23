package com.keita.filingcabinet.mockData;

import com.keita.filingcabinet.model.dto.FileCreation;
import com.keita.filingcabinet.model.dto.PagingRequest;
import com.keita.filingcabinet.model.entity.File;
import com.keita.filingcabinet.model.enums.Role;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.util.BsonUtils;
import org.springframework.mock.web.MockMultipartFile;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class FileMockData {


    public static MockMultipartFile getMockMultipartFile() {
        return new MockMultipartFile("file.pdf", "file.pdf", "applicaiton/pdf", "".getBytes());
    }

    public static FileCreation getFileCreation(MockMultipartFile mockMultipartFile) {
        return FileCreation.builder()
                .folderId("61621ca50545544ead443f75")
                .description("test")
                .multipartFile(mockMultipartFile)
                .build();

    }

    public static File getFile() {
        return File.builder()
                .folderId("61621ca50545544ead443f75")
                .description("test")
                .fileName("test.txt")
                .uploadBy(Collections.singletonMap("employee1", Role.USER.toString()))
                .isActive(true)
                .build();
    }

    public static MockMultipartFile getWrongMockMultipartFile() {
        return new MockMultipartFile("malware.py", "malware.py", "application/json", "a".getBytes());
    }

    public static GridFSFile getGridFsFile() {
        Map<String, Object> documentMap = new LinkedHashMap<>();

        documentMap.put("description", "none");
        documentMap.put("uploadBy", Collections.singletonMap("employee1", Role.USER.toString()));
        documentMap.put("isActive", true);

        return new GridFSFile(
                BsonUtils.simpleToBsonValue(new ObjectId("507f1f77bcf86cd799439011")),
                "file.pdf",
                291980L,
                261120,
                new Date(),
                new Document(documentMap)
        );
    }

    public static GridFSFile getGridFsFileDisable() {
        Map<String, Object> documentMap = new LinkedHashMap<>();

        documentMap.put("description", "none");
        documentMap.put("uploadBy", Collections.singletonMap("employee1", Role.USER.toString()));
        documentMap.put("isActive", false);
        documentMap.put("deActivationDate", LocalDateTime.now());

        return new GridFSFile(
                BsonUtils.simpleToBsonValue(new ObjectId("507f1f77bcf86cd799439011")),
                "file.pdf",
                291980L,
                261120,
                new Date(),
                new Document(documentMap)
        );
    }

    public static PagingRequest getPagingRequest() {
        return PagingRequest.builder()
                .noPage(3)
                .size(10)
                .build();
    }

}
