package com.keita.filingcabinet.mockData;

import com.keita.filingcabinet.model.dto.FileCreation;
import com.keita.filingcabinet.model.dto.FileDetail;
import com.keita.filingcabinet.model.dto.PagingRequest;
import com.keita.filingcabinet.model.entity.File;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.gridfs.GridFSFindIterable;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.bson.BsonArray;
import org.bson.BsonValue;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.util.BsonUtils;
import org.springframework.mock.web.MockMultipartFile;

import java.util.*;

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

    public static File getFile(){
        return File.builder()
                .folderId("1231332erw")
                .description("test")
                .uploadBy("employee1")
                .build();
    }

    public static MockMultipartFile getWrongMockMultipartFile(){
        return new MockMultipartFile("malware.py","malware.py","application/json","".getBytes());
    }

    public static GridFSFile getGridFsFile(){
        Map<String,Object> documentMap = new LinkedHashMap<>();

        documentMap.put("description","none");
        documentMap.put("uploadBy", "marck");

        return new GridFSFile(
                BsonUtils.simpleToBsonValue(new ObjectId("507f1f77bcf86cd799439011")),
                "file.pdf",
                291980L,
                261120,
                new Date(),
                new Document(documentMap)
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
