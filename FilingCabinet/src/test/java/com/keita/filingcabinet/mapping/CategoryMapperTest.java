package com.keita.filingcabinet.mapping;

import com.keita.filingcabinet.mockData.CategoryMockData;
import com.keita.filingcabinet.model.dto.CategoryDetailSuperUserView;
import com.keita.filingcabinet.model.dto.CategoryDetailUserView;
import com.keita.filingcabinet.model.entity.Category;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoryMapperTest {

    @Test
    void toCategoryDetailUserView() {
        //ARRANGE
        Category category = CategoryMockData.getListCategoryForRepoTest().get(0);

        //ACT
        CategoryDetailUserView categoryDetailUserView = CategoryMapper.toCategoryDetailUserView(category);

        //ASSERT
        assertEquals(category.getId(), categoryDetailUserView.getId());
        assertEquals(category.getName(), categoryDetailUserView.getName());
    }

    @Test
    void toCategoryDetailSuperUserView() {
        //ARRANGE
        Category category = CategoryMockData.getListCategoryForRepoTest().get(0);

        //ACT
        CategoryDetailSuperUserView categoryDetailSuperUserView = CategoryMapper.toCategoryDetailSuperUserView(category);

        //ASSERT
        assertEquals(category.getId(), categoryDetailSuperUserView.getId());
        assertEquals(category.getName(), categoryDetailSuperUserView.getName());
        assertEquals(category.getCreationDate(), categoryDetailSuperUserView.getCreationDate());
        assertEquals(category.getCreatedBy(), categoryDetailSuperUserView.getCreatedBy());
        assertEquals(category.getDeactivationDate(), categoryDetailSuperUserView.getDeactivationDate());
        assertEquals(category.getDeactivatedBy(), categoryDetailSuperUserView.getDeactivatedBy());
    }
}
