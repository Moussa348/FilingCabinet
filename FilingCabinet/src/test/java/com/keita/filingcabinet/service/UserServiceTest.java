package com.keita.filingcabinet.service;

import com.keita.filingcabinet.exception.WrongCredentialsException;
import com.keita.filingcabinet.mockData.CategoryMockData;
import com.keita.filingcabinet.mockData.FileMockData;
import com.keita.filingcabinet.mockData.UserMockData;
import com.keita.filingcabinet.model.dto.AuthRequest;
import com.keita.filingcabinet.model.dto.CategoryDetailUserView;
import com.keita.filingcabinet.model.dto.FileDetailUserView;
import com.keita.filingcabinet.model.dto.PagingRequest;
import com.keita.filingcabinet.model.entity.User;
import com.keita.filingcabinet.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    FileService fileService;

    @Mock
    CategoryService categoryService;

    @InjectMocks
    UserService userService;

    @Test
    void getListFileDetailUserView() {
        //ARRANGE
        PagingRequest pagingRequest = FileMockData.getPagingRequest();

        when(fileService.getListFile(any())).thenReturn(Arrays.asList(FileMockData.getGridFsFile()));

        //ACT
        List<FileDetailUserView> fileDetailUserViews = userService.getListFileDetailUserView(pagingRequest);

        //ASSERT
        assertEquals(1, fileDetailUserViews.size());
    }

    @Test
    void getListCategoryDetailUserView(){
        //ARRANGE
        when(categoryService.getListCategory(true)).thenReturn(CategoryMockData.getListCategoryForRepoTest());

        //ACT
        List<CategoryDetailUserView> categoryDetailUserViews = userService.getListCategoryDetailUserView();

        //ASSERT
        assertEquals(2,categoryDetailUserViews.size());
    }

    @Test
    void shouldFindUserByEmailAndPassword() throws WrongCredentialsException {
        //ARRANGE
        User user = UserMockData.getUser();

        when(userRepository.findByEmailAndPasswordAndIsActiveTrueAndIsAccountVerifiedTrue(user.getEmail(),user.getPassword()))
                .thenReturn(Optional.of(user));

        //ACT
        User userFound = userService.findUserByEmailAndPassword(user.getEmail(),user.getPassword());

        //ASSERT
        assertNotNull(userFound);
    }

    @Test
    void shouldNotFindUserByEmailAndPassword(){
        //ARRANGE
        when(userRepository.findByEmailAndPasswordAndIsActiveTrueAndIsAccountVerifiedTrue(any(),any()))
                .thenReturn(Optional.empty());

        //ASSERT

        assertThrows(WrongCredentialsException.class,() -> userService.findUserByEmailAndPassword(any(),any()));
    }

}
