package com.keita.filingcabinet.service;

import com.keita.filingcabinet.exception.AppropriateFileException;
import com.keita.filingcabinet.mockData.FileMockData;
import com.keita.filingcabinet.model.entity.File;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FileServiceTest {

    @Mock
    GridFsTemplate gridFsTemplate;

    @InjectMocks
    FileService fileService;

    @Test
    void shouldUpload() throws IOException, AppropriateFileException {
        //ARRANGE
        String id = "5399aba6e4b0ae375bfdca88";
        ObjectId objectId = new ObjectId(id);
        MockMultipartFile mockMultipartFile = FileMockData.getMockMultipartFile();

        when(gridFsTemplate.store(any(), any(), any(), any(File.class)))
                .thenReturn(objectId);

        //ACT
        String returnedId = fileService.upload(mockMultipartFile);

        //ASSERT
        assertEquals(id, returnedId);
    }

    @Test
    void shouldNotUpload(){
        //ARRANGE
        String id = "5399aba6e4b0ae375bfdca88";
        ObjectId objectId = new ObjectId(id);
        MockMultipartFile wrongMockMultipartFile = FileMockData.getWrongMockMultipartFile();

        //ASSERT
        assertThrows(AppropriateFileException.class,()->fileService.upload(wrongMockMultipartFile));
    }

    @Test
    void download() throws IOException {
        //ARRANGE
        GridFSFile gridFSFile = FileMockData.getGridFsFile();
        GridFsResource gridFsResource = new GridFsResource(gridFSFile,FileMockData.getMockMultipartFile().getInputStream());

        when(gridFsTemplate.getResource(gridFSFile)).thenReturn(gridFsResource);

        //ACT
        ByteArrayResource byteArrayResource = fileService.download(gridFSFile);

        //ASSERT
        assertNotNull(byteArrayResource);
    }

    @Test
    void getGridFsFile() {
        //ARRANGE

        //ACT

        //ASSERT

    }
}
