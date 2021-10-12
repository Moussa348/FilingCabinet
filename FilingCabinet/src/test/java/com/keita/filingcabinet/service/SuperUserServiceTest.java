package com.keita.filingcabinet.service;

import com.keita.filingcabinet.mockData.FileMockData;
import com.keita.filingcabinet.model.dto.FileDetailSuperUserView;
import com.keita.filingcabinet.model.dto.FileDetailUserView;
import com.keita.filingcabinet.model.dto.PagingRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SuperUserServiceTest {

    @Mock
    FileService fileService;

    @InjectMocks
    SuperUserService superUserService;

    @Test
    void getListFileDetailSuperUserView(){
        //ARRANGE
        PagingRequest pagingRequest = FileMockData.getPagingRequest();

        when(fileService.getListFile(any())).thenReturn(Arrays.asList(FileMockData.getGridFsFile()));

        //ACT
        List<FileDetailSuperUserView> fileDetailUserViews = superUserService.getListFileDetailSuperUserView(pagingRequest);

        //ASSERT
        assertEquals(1, fileDetailUserViews.size());

    }
}
