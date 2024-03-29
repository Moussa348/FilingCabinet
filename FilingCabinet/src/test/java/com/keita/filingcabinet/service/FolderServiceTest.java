package com.keita.filingcabinet.service;

import com.keita.filingcabinet.mockData.FolderMockData;
import com.keita.filingcabinet.model.dto.FolderDetail;
import com.keita.filingcabinet.model.entity.Folder;
import com.keita.filingcabinet.repository.FolderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

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


    @Test
    void shouldGetListFolderByPatientId(){
        //ARRANGE
        String patientId = "61660d5c88c87f5d2835a4ff";

        when(folderRepository.findAllByPatientId(patientId)).thenReturn(FolderMockData.getListFolder());

        //ACT
        List<Folder> folders = folderService.getListFolderByPatientId(patientId);

        //ASSERT
        assertEquals(3,folders.size());
    }

    @Test
    void shouldGetMapFolderByPatientId(){
        //ARRANGE
        String patientId = "61660d5c88c87f5d2835a4ff";

        when(folderRepository.findAllByPatientId(patientId)).thenReturn(FolderMockData.getListFolder());

        //ACT
        Map<String, String> mapFolders = folderService.getMapFolderByPatientId(patientId);

        //ASSERT
        assertEquals(3,mapFolders.size());
    }

}
