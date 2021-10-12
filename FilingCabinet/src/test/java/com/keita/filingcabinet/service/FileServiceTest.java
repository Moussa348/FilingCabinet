package com.keita.filingcabinet.service;

import com.keita.filingcabinet.exception.AppropriateFileException;
import com.keita.filingcabinet.exception.FileNotFoundException;
import com.keita.filingcabinet.mockData.FileMockData;
import com.keita.filingcabinet.model.dto.FileCreation;
import com.keita.filingcabinet.model.entity.File;
import com.keita.filingcabinet.model.enums.OperationType;
import com.mongodb.client.gridfs.GridFSFindIterable;
import com.mongodb.client.gridfs.model.GridFSFile;
import lombok.extern.java.Log;
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
import org.springframework.test.context.ContextConfiguration;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@Log
@ContextConfiguration
@ExtendWith(MockitoExtension.class)
public class FileServiceTest {

    @Mock
    GridFsTemplate gridFsTemplate;

    @Mock
    LogService logService;

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

        doNothing().when(logService).add(anyString(), any(OperationType.class));

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

        //ASSERT
        assertThrows(AppropriateFileException.class, () -> fileService.upload(fileCreation));
    }

    @Test
    void download() throws IOException {
        //ARRANGE
        GridFSFile gridFSFile = FileMockData.getGridFsFile();
        GridFsResource gridFsResource = new GridFsResource(gridFSFile, FileMockData.getMockMultipartFile().getInputStream());

        doNothing().when(logService).add(anyString(), any(OperationType.class));
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
        ObjectId objectId = new ObjectId(id);

        when(gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)))).thenReturn(null);

        //ASSERT
        assertThrows(FileNotFoundException.class, () -> fileService.getGridFsFile(id));
    }


    @Test
    void shouldGetListFile() {
        //ARRANGE
        GridFSFindIterable gridFSFindIterable = mock(GridFSFindIterable.class);

        when(gridFsTemplate.find(any())).thenReturn(gridFSFindIterable);

        //ACT
        List<GridFSFile> files = fileService.getListFile(any());

        //ASSERT
        assertEquals(0, files.size());
    }

    @Test
    void shouldDisable() throws FileNotFoundException, IOException {
        //ARRANGE
        String id = "5399aba6e4b0ae375bfdca88";
        ObjectId objectId = new ObjectId(id);
        GridFSFile gridFSFile = FileMockData.getGridFsFileDisable();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("".getBytes());
        GridFsResource gridFsResource = new GridFsResource(gridFSFile, byteArrayInputStream);

        when(gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)))).thenReturn(gridFSFile);
        when((gridFsTemplate.getResource(gridFSFile))).thenReturn(gridFsResource);

        when(gridFsTemplate.store(byteArrayInputStream, gridFSFile.getFilename(), gridFSFile.getMetadata())).thenReturn(objectId);
        doNothing().when(logService).add(objectId.toString(), OperationType.DISABLE);
        lenient().doNothing().when(gridFsTemplate).delete(new Query(Criteria.where("_id").is(objectId)));

        //ACT
        fileService.disable(id);

        //ASSERT
        assertFalse(gridFSFile.getMetadata().getBoolean("isActive"));
    }

    @Test
    void shouldNotDisable() {
        //ARRANGE
        String id = "5399aba6e4b0ae375bfdca88";

        when(gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)))).thenReturn(null);

        //ASSERT
        assertThrows(FileNotFoundException.class, () -> fileService.disable(id));
    }

    @Test
    void shouldEnable() throws FileNotFoundException, IOException {
        //ARRANGE
        String id = "5399aba6e4b0ae375bfdca88";
        ObjectId objectId = new ObjectId(id);
        GridFSFile gridFSFile = FileMockData.getGridFsFileDisable();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("".getBytes());
        GridFsResource gridFsResource = new GridFsResource(gridFSFile, byteArrayInputStream);

        when(gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)))).thenReturn(gridFSFile);
        when((gridFsTemplate.getResource(gridFSFile))).thenReturn(gridFsResource);

        when(gridFsTemplate.store(byteArrayInputStream, gridFSFile.getFilename(), gridFSFile.getMetadata())).thenReturn(objectId);
        doNothing().when(logService).add(objectId.toString(), OperationType.ENABLE);
        lenient().doNothing().when(gridFsTemplate).delete(new Query(Criteria.where("_id").is(objectId)));

        //ACT
        fileService.enable(id);

        //ASSERT
        assertTrue(gridFSFile.getMetadata().getBoolean("isActive"));
    }

    @Test
    void shouldNotEnable() {
        //ARRANGE
        String id = "5399aba6e4b0ae375bfdca88";

        when(gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)))).thenReturn(null);

        //ASSERT
        assertThrows(FileNotFoundException.class, () -> fileService.enable(id));
    }

}
