package com.keita.filingcabinet.service;

import com.keita.filingcabinet.exception.CategoryNotFoundException;
import com.keita.filingcabinet.mockData.CategoryMockData;
import com.keita.filingcabinet.model.entity.Category;
import com.keita.filingcabinet.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

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

    @Test
    void shouldGetListCategoryIsActiveFalseAndTrue() {
        //ARRANGE
        when(categoryRepository.findAll()).thenReturn(CategoryMockData.getListCategoryForRepoTest());

        //ACT
        List<Category> categories = categoryService.getListCategory(false);

        //ASSERT
        assertEquals(2, categories.size());
    }

    @Test
    void shouldGetListCategoryIsActiveTrue() {
        //ARRANGE
        when(categoryRepository.findAllByIsActiveTrue()).thenReturn(CategoryMockData.getListCategoryForRepoTest());

        //ACT
        List<Category> categories = categoryService.getListCategory(true);

        //ASSERT
        assertEquals(2, categories.size());
    }

    @Test
    void shouldGetListCategoryName() {
        //ARRANGE
        when(categoryRepository.findAllByIsActiveTrue()).thenReturn(CategoryMockData.getListCategoryForRepoTest());

        //ACT
        List<String> categoriesName = categoryService.getListCategoryName();

        //ASSERT
        assertEquals(2, categoriesName.size());
    }

    @Test
    void shouldDisable() throws CategoryNotFoundException {
        //ARRANGE
        Category category = CategoryMockData.getListCategoryForRepoTest().get(0);

        when(categoryRepository.findById(category.getId())).thenReturn(Optional.of(category));
        when(categoryRepository.save(any())).thenReturn(category);

        //ACT
        String id = categoryService.disable(category.getId());

        //ASSERT
        assertEquals(category.getId(), id);
        assertFalse(category.getIsActive());
    }

    @Test
    void shouldNotDisable() {
        //ARRANGE
        Category category = CategoryMockData.getListCategoryForRepoTest().get(0);

        when(categoryRepository.findById(category.getId())).thenReturn(Optional.empty());

        //ASSERT
        assertThrows(CategoryNotFoundException.class, () -> categoryService.disable(category.getId()));
    }

    @Test
    void shouldEnable() throws CategoryNotFoundException {
        //ARRANGE
        Category category = CategoryMockData.getListCategoryForRepoTest().get(0);

        when(categoryRepository.findById(category.getId())).thenReturn(Optional.of(category));
        when(categoryRepository.save(any())).thenReturn(category);

        //ACT
        String id = categoryService.enable(category.getId());

        //ASSERT
        assertEquals(category.getId(), id);
        assertTrue(category.getIsActive());
    }

    @Test
    void shouldNotEnable() {
        //ARRANGE
        Category category = CategoryMockData.getListCategoryForRepoTest().get(0);

        when(categoryRepository.findById(category.getId())).thenReturn(Optional.empty());

        //ASSERT
        assertThrows(CategoryNotFoundException.class, () -> categoryService.enable(category.getId()));
    }

}
