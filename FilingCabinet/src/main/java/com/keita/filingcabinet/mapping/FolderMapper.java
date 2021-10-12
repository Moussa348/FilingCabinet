package com.keita.filingcabinet.mapping;

import com.keita.filingcabinet.model.dto.FolderDetail;
import com.keita.filingcabinet.model.entity.Folder;

public abstract class FolderMapper {

    public static FolderDetail toFolderDetail(Folder folder){
        return FolderDetail.builder()
                .categoryName(folder.getCategoryName())
                .creationDate(folder.getCreationDate())
                .build();
    }
}
