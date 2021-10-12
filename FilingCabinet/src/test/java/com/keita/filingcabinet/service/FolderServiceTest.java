package com.keita.filingcabinet.service;

import com.keita.filingcabinet.mockData.FolderMockData;
import com.keita.filingcabinet.model.entity.Folder;
import com.keita.filingcabinet.repository.FolderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class FolderServiceTest {

    @Mock
    FolderRepository folderRepository;

    @InjectMocks
    FolderService folderService;

    @Test
    void shouldCreateFolder(){
        //ARRANGE
        Folder folder = FolderMockData.getFolder();

        when(folderRepository.save(any())).thenReturn(folder);

        //ACT
        String id = folderService.createFolder(folder.getPatientId(),folder.getCategoryName());

        //ASSERT
        assertEquals(folder.getId(),id);
    }

}
