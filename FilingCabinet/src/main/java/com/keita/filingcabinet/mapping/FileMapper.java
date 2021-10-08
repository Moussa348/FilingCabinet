package com.keita.filingcabinet.mapping;

import com.keita.filingcabinet.model.dto.FileCreation;
import com.keita.filingcabinet.model.dto.FileDetail;
import com.keita.filingcabinet.model.entity.File;

import java.time.LocalDateTime;

public abstract class FileMapper {


    public static File toFile(FileCreation fileCreation){
        return File.builder()
                .folderId(fileCreation.getFolderId())
                .description(fileCreation.getDescription())
                .uploadBy(fileCreation.getUploadBy())
                .uploadDate(LocalDateTime.now())
                .build();

    }


    public static FileDetail toFileDetailView(File file){
        return FileDetail.builder()
                .id(file.getId())
                .filename(file.getFileName())
                .description(file.getDescription())
                .uploadBy(file.getUploadBy())
                .build();
    }
}
