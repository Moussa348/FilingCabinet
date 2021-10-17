package com.keita.filingcabinet.mapping;

import com.keita.filingcabinet.model.dto.FileCreation;
import com.keita.filingcabinet.model.dto.FileDetailSuperUserView;
import com.keita.filingcabinet.model.dto.FileDetailUserView;
import com.keita.filingcabinet.model.entity.File;
import com.keita.filingcabinet.util.DateUtil;
import com.mongodb.client.gridfs.model.GridFSFile;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

public abstract class FileMapper {

    public static File toFile(FileCreation fileCreation) {
        return File.builder()
                .folderId(fileCreation.getFolderId())
                .description(fileCreation.getDescription())
                .isActive(true)
                .build();

    }


    public static FileDetailUserView toFileDetailUserView(GridFSFile gridFSFile) {
        return FileDetailUserView.builder()
                .id(gridFSFile.getObjectId().toString())
                .filename(gridFSFile.getFilename())
                .description(gridFSFile.getMetadata().getString("description"))
                .uploadBy(gridFSFile.getMetadata().get("uploadBy", Map.class))
                .uploadDate(DateUtil.convertDateToLocalDateTime(gridFSFile.getUploadDate()))
                .build();
    }

    public static FileDetailSuperUserView toFileDetailSuperUserView(GridFSFile gridFSFile) {
        Date deactivationDate = gridFSFile.getMetadata().getDate("deActivationDate");
        return FileDetailSuperUserView.builder()
                .id(gridFSFile.getObjectId().toString())
                .filename(gridFSFile.getFilename())
                .description(gridFSFile.getMetadata().getString("description"))
                .uploadBy(gridFSFile.getMetadata().get("uploadBy", Map.class))
                .uploadDate(DateUtil.convertDateToLocalDateTime(gridFSFile.getUploadDate()))
                .deactivationDate(deactivationDate != null ?DateUtil.convertDateToLocalDateTime(deactivationDate):null)
                .build();

    }

}
