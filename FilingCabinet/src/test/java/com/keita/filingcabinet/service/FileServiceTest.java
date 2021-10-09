package com.keita.filingcabinet.service;

import com.keita.filingcabinet.exception.AppropriateFileException;
import com.keita.filingcabinet.exception.FileNotFoundException;
import com.keita.filingcabinet.mockData.FileMockData;
import com.keita.filingcabinet.model.dto.FileCreation;
import com.keita.filingcabinet.model.entity.File;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

import java.io.IOException;

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
        FileCreation fileCreation = FileMockData.getFileCreation(FileMockData.getMockMultipartFile());
        ObjectId objectId = new ObjectId(id);

        when(gridFsTemplate.store(any(), any(), any(), any(File.class)))
                .thenReturn(objectId);

        //ACT
        String returnedId = fileService.upload(fileCreation);

        //ASSERT
        assertEquals(id, returnedId);
    }

    @Test
    void shouldNotUpload() {
        //ARRANGE
        String id = "5399aba6e4b0ae375bfdca88";
        FileCreation fileCreation = FileMockData.getFileCreation(FileMockData.getWrongMockMultipartFile());
        ObjectId objectId = new ObjectId(id);

        //ASSERT
        assertThrows(AppropriateFileException.class, () -> fileService.upload(fileCreation));
    }

    @Test
    void download() throws IOException {
        //ARRANGE
        GridFSFile gridFSFile = FileMockData.getGridFsFile();
        GridFsResource gridFsResource = new GridFsResource(gridFSFile, FileMockData.getMockMultipartFile().getInputStream());

        when(gridFsTemplate.getResource(gridFSFile)).thenReturn(gridFsResource);

        //ACT
        ByteArrayResource byteArrayResource = fileService.download(gridFSFile);

        //ASSERT
        assertNotNull(byteArrayResource);
    }

    @Test
    void shouldGetGridFsFile() throws FileNotFoundException {
        //ARRANGE
        String id = "5399aba6e4b0ae375bfdca88";
        GridFSFile gridFSFile = FileMockData.getGridFsFile();

        when(gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)))).thenReturn(gridFSFile);

        //ACT
        GridFSFile returnedGridFsFile = fileService.getGridFsFile(id);

        //ASSERT
        assertEquals(gridFSFile.getFilename(), returnedGridFsFile.getFilename());
    }

    @Test
    void shouldNotGetGridFsFile() {
        //ARRANGE
        String id = "5399aba6e4b0ae375bfdca88";

        when(gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)))).thenReturn(null);

        //ASSERT
        assertThrows(FileNotFoundException.class, () -> fileService.getGridFsFile(id));
    }
}
