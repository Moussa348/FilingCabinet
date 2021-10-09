package com.keita.filingcabinet.mockData;

import com.keita.filingcabinet.model.dto.FileCreation;
import com.keita.filingcabinet.model.dto.PagingRequest;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.gridfs.GridFSFindIterable;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.bson.BsonArray;
import org.bson.Document;
import org.springframework.mock.web.MockMultipartFile;

import java.util.Date;

public abstract class FileMockData {


    public static MockMultipartFile getMockMultipartFile(){
        return new MockMultipartFile("file.pdf","file.pdf","applicaiton/pdf","".getBytes());
    }

    public static FileCreation getFileCreation(MockMultipartFile mockMultipartFile){
        return FileCreation.builder()
                .folderId("1231332erw")
                .description("test")
                .uploadBy("employee1")
                .multipartFile(mockMultipartFile)
                .build();

    }

    public static MockMultipartFile getWrongMockMultipartFile(){
        return new MockMultipartFile("malware.py","malware.py","application/json","".getBytes());
    }

    public static GridFSFile getGridFsFile(){
        return new GridFSFile(
                new BsonArray(),
                "file.pdf",
                291980L,
                261120,
                new Date(),
                new Document("uploadBy","marc")

        );
    }

    public static PagingRequest getPagingRequest(){
        return PagingRequest.builder()
                .folderId("89dasuhjk32madjasd")
                .noPage(3)
                .size(10)
                .build();
    }

}
