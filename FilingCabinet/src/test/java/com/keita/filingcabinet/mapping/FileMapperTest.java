package com.keita.filingcabinet.mapping;

import com.keita.filingcabinet.mockData.FileMockData;
import com.keita.filingcabinet.model.dto.FileCreation;
import com.keita.filingcabinet.model.dto.FileDetailSuperUserView;
import com.keita.filingcabinet.model.dto.FileDetailUserView;
import com.keita.filingcabinet.model.entity.File;
import com.keita.filingcabinet.util.DateUtil;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class FileMapperTest {

    @Test
    void toFile(){
        //ARRANGE
        FileCreation fileCreation = FileMockData.getFileCreation(FileMockData.getMockMultipartFile());

        //ACT
        File file = FileMapper.toFile(fileCreation);

        //ASSERT
        assertEquals(fileCreation.getFolderId(),file.getFolderId());
        assertEquals(fileCreation.getDescription(),file.getDescription());
        assertEquals(fileCreation.getUploadBy(),file.getUploadBy());
        assertTrue(file.getIsActive());
    }

    @Test
    void toFileDetailUserView(){
        //ARRANGE
        GridFSFile gridFSFile = FileMockData.getGridFsFile();

        //ACT
        FileDetailUserView fileDetailUserView = FileMapper.toFileDetailUserView(gridFSFile);

        //ASSERT
        assertEquals(gridFSFile.getObjectId().toString(), fileDetailUserView.getId());
        assertEquals(gridFSFile.getFilename(), fileDetailUserView.getFilename());
        assertEquals(gridFSFile.getMetadata().get("description", String.class), fileDetailUserView.getDescription());
        assertEquals(gridFSFile.getMetadata().get("uploadBy", Map.class), fileDetailUserView.getUploadBy());
        assertEquals(DateUtil.convertDateToLocalDateTime(gridFSFile.getUploadDate()), fileDetailUserView.getUploadDate());
    }

    @Test
    void toFileDetailSuperUserView(){
        //ARRANGE
        GridFSFile gridFSFile = FileMockData.getGridFsFile();

        //ACT
        FileDetailSuperUserView fileDetailSuperUserView = FileMapper.toFileDetailSuperUserView(gridFSFile);

        //ASSERT
        assertEquals(gridFSFile.getObjectId().toString(), fileDetailSuperUserView.getId());
        assertEquals(gridFSFile.getFilename(), fileDetailSuperUserView.getFilename());
        assertEquals(gridFSFile.getMetadata().get("description", String.class), fileDetailSuperUserView.getDescription());
        assertEquals(gridFSFile.getMetadata().get("uploadBy", Map.class), fileDetailSuperUserView.getUploadBy());
        assertEquals(DateUtil.convertDateToLocalDateTime(gridFSFile.getUploadDate()), fileDetailSuperUserView.getUploadDate());
        assertNull(fileDetailSuperUserView.getDeactivationDate());
    }

}
