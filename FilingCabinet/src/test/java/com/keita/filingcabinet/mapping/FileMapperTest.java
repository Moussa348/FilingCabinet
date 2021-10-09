package com.keita.filingcabinet.mapping;

import com.keita.filingcabinet.mockData.FileMockData;
import com.keita.filingcabinet.model.dto.FileCreation;
import com.keita.filingcabinet.model.dto.FileDetail;
import com.keita.filingcabinet.model.entity.File;
import com.keita.filingcabinet.util.DateUtil;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    }

    @Test
    void toFileDetail(){
        //ARRANGE
        GridFSFile gridFSFile = FileMockData.getGridFsFile();

        //ACT
        FileDetail fileDetail = FileMapper.toFileDetail(gridFSFile);

        //ASSERT
        assertEquals(gridFSFile.getObjectId().toString(),fileDetail.getId());
        assertEquals(gridFSFile.getFilename(),fileDetail.getFilename());
        assertEquals(gridFSFile.getMetadata().get("description", String.class),fileDetail.getDescription());
        assertEquals(gridFSFile.getMetadata().get("uploadBy", String.class),fileDetail.getUploadBy());
        assertEquals(DateUtil.convertDateToLocalDateTime(gridFSFile.getUploadDate()),fileDetail.getUploadDate());
    }

}
