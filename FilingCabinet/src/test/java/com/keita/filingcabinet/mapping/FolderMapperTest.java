package com.keita.filingcabinet.mapping;

import com.keita.filingcabinet.mockData.FolderMockData;
import com.keita.filingcabinet.model.dto.FolderDetail;
import com.keita.filingcabinet.model.entity.Folder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FolderMapperTest {

    @Test
    void toFolderDetail() {
        //ARRANGE
        Folder folder = FolderMockData.getFolder();

        //ACT
        FolderDetail folderDetail = FolderMapper.toFolderDetail(folder);

        //ASSERT
        assertEquals(folder.getCategoryName(), folderDetail.getCategoryName());
        assertEquals(folder.getCreationDate(), folderDetail.getCreationDate());
    }
}
