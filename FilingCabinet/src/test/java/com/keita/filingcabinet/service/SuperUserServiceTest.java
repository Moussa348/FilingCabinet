package com.keita.filingcabinet.service;

import com.keita.filingcabinet.mockData.CategoryMockData;
import com.keita.filingcabinet.mockData.FileMockData;
import com.keita.filingcabinet.model.dto.*;
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

    @Mock
    CategoryService categoryService;

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

    @Test
    void getListCategoryDetailSuperUserView(){
        //ARRANGE
        when(categoryService.getListCategory(false)).thenReturn(CategoryMockData.getListCategoryForRepoTest());

        //ACT
        List<CategoryDetailSuperUserView> categoryDetailUserViews = superUserService.getListCategoryDetailSuperUserView();

        //ASSERT
        assertEquals(2,categoryDetailUserViews.size());
    }
}
