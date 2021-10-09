package com.keita.filingcabinet.mapping;

import com.keita.filingcabinet.model.dto.FileCreation;
import com.keita.filingcabinet.model.dto.FileDetail;
import com.keita.filingcabinet.model.entity.File;
import com.keita.filingcabinet.util.DateUtil;
import com.mongodb.client.gridfs.model.GridFSFile;

import java.time.LocalDateTime;
import java.util.Date;

public abstract class FileMapper {


    public static File toFile(FileCreation fileCreation){
        return File.builder()
                .folderId(fileCreation.getFolderId())
                .description(fileCreation.getDescription())
                .uploadBy(fileCreation.getUploadBy())
                .build();

    }


    public static FileDetail toFileDetail(GridFSFile gridFSFile){
        return FileDetail.builder()
                .id(gridFSFile.getObjectId().toString())
                .filename(gridFSFile.getFilename())
                .description(gridFSFile.getMetadata().get("description",String.class))
                .uploadBy(gridFSFile.getMetadata().get("uploadBy",String.class))
                .uploadDate(DateUtil.convertDateToLocalDateTime(gridFSFile.getUploadDate()))
                .build();
    }
}
