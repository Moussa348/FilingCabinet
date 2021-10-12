package com.keita.filingcabinet.repository;

import com.keita.filingcabinet.mockData.CategoryMockData;
import com.keita.filingcabinet.model.entity.Category;
import lombok.extern.java.Log;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Log
@DataMongoTest
@ActiveProfiles("none")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CategoryRepositoryTest {

    @Autowired
    CategoryRepository categoryRepository;

    @BeforeAll
    void init() {
        categoryRepository.saveAll(CategoryMockData.getListCategoryForRepoTest());
    }

    @Test
    void shouldExistsByName() {
        //ARRANGE
        String name = "evaluation";

        //ACT
        boolean existByName = categoryRepository.existsByName(name);

        //ASSERT
        assertTrue(existByName);
    }

    @Test
    void shouldNotExistsByName() {
        //ARRANGE
        String name = "non_existent";

        //ACT
        boolean existByName = categoryRepository.existsByName(name);

        //ASSERT
        assertFalse(existByName);
    }

    @Test
    void shouldFindAllByIsActiveTrue() {
        //ACT
        List<Category> categories = categoryRepository.findAllByIsActiveTrue();

        //ASSERT
        assertEquals(2, categories.size());
    }

}
