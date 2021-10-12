package com.keita.filingcabinet.mapping;

import com.keita.filingcabinet.model.dto.CategoryDetailSuperUserView;
import com.keita.filingcabinet.model.dto.CategoryDetailUserView;
import com.keita.filingcabinet.model.entity.Category;

public abstract class CategoryMapper {

    public static CategoryDetailUserView toCategoryDetailUserView(Category category){
        return CategoryDetailUserView.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    public static CategoryDetailSuperUserView toCategoryDetailSuperUserView(Category category){
        return CategoryDetailSuperUserView.builder()
                .id(category.getId())
                .name(category.getName())
                .creationDate(category.getCreationDate())
                .createdBy(category.getCreatedBy())
                .deactivationDate(category.getDeactivationDate())
                .deactivatedBy(category.getDeactivatedBy())
                .build();
    }
}
