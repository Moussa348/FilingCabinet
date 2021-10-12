package com.keita.filingcabinet.service;

import com.keita.filingcabinet.mockData.CategoryMockData;
import com.keita.filingcabinet.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

    @Mock
    CategoryRepository categoryRepository;

    @InjectMocks
    CategoryService categoryService;

    @Test
    void shouldCreateCategory() {
        //ARRANGE
        String name = "Prise de sang";

        when(categoryRepository.save(any())).thenReturn(CategoryMockData.getListCategoryForRepoTest().get(0));

        //ACT
        String id = categoryService.createCategory(name);

        //ASSERT
        assertNotNull(id);
    }

}
