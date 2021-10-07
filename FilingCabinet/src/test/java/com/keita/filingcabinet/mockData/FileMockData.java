package com.keita.filingcabinet.mockData;

import com.mongodb.client.gridfs.model.GridFSFile;
import org.bson.BsonArray;
import org.bson.Document;
import org.springframework.mock.web.MockMultipartFile;

import java.util.Date;

public abstract class FileMockData {


    public static MockMultipartFile getMockMultipartFile(){
        return new MockMultipartFile("file.pdf","file.pdf","applicaiton/pdf","".getBytes());
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
}
