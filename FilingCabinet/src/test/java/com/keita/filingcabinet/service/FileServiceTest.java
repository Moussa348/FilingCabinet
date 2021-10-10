package com.keita.filingcabinet.service;

import com.keita.filingcabinet.exception.AppropriateFileException;
import com.keita.filingcabinet.exception.FileNotFoundException;
import com.keita.filingcabinet.mockData.FileMockData;
import com.keita.filingcabinet.model.dto.FileCreation;
import com.keita.filingcabinet.model.dto.FileDetail;
import com.keita.filingcabinet.model.dto.PagingRequest;
import com.keita.filingcabinet.model.entity.File;
import com.keita.filingcabinet.model.enums.OperationType;
import com.mongodb.client.gridfs.GridFSFindIterable;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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

        doNothing().when(logService).add(anyString(), anyString(), any(OperationType.class));

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

        doNothing().when(logService).add(anyString(), anyString(), any(OperationType.class));
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
        doNothing().when(logService).add(anyString(), anyString(), any(OperationType.class));

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
    void shouldGetListFileDetail(){
        //ARRANGE
        PagingRequest pagingRequest = FileMockData.getPagingRequest();
        Query query = new Query(Criteria.where("metadata.folderId").is(pagingRequest.getFolderId()))
                .with(PageRequest.of(pagingRequest.getNoPage(), pagingRequest.getSize(), Sort.by("uploadDate")));

        //ACT
        List<FileDetail> fileDetails = fileService.getListFileDetail(pagingRequest);

        //ASSERT

    }
    @Test
    void shouldDisable() throws FileNotFoundException, IOException {
        //ARRANGE
        String id = "5399aba6e4b0ae375bfdca88";
        ObjectId objectId = new ObjectId(id);
        GridFSFile gridFSFile = FileMockData.getGridFsFile();
        GridFsResource gridFsResource = new GridFsResource(gridFSFile, FileMockData.getMockMultipartFile().getInputStream());

        when(gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)))).thenReturn(gridFSFile);
        when(gridFsTemplate.getResource(gridFSFile)).thenReturn(gridFsResource);

        lenient().when(gridFsTemplate.store(any(InputStream.class), any(String.class), any(String.class))).thenReturn(objectId);
        lenient().doNothing().when(gridFsTemplate).delete(new Query(Criteria.where("_id").is(objectId)));

        //ACT
        fileService.disable(id);

        //ASSERT
        assertFalse(gridFSFile.getMetadata().getBoolean("isActive"));
        assertTrue(gridFSFile.getMetadata().getBoolean("hasBeenUpdated"));
    }

    @Test
    void shouldNotDisable() {
        //ARRANGE
        String id = "5399aba6e4b0ae375bfdca88";

        when(gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)))).thenReturn(null);

        //ASSERT
        assertThrows(FileNotFoundException.class,()-> fileService.disable(id));
    }

}
