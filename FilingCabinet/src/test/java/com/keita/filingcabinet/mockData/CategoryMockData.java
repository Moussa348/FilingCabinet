package com.keita.filingcabinet.mockData;

import com.keita.filingcabinet.model.entity.Category;

import java.util.Arrays;
import java.util.List;

public abstract class CategoryMockData {

    public static List<Category> getListCategoryForRepoTest(){
        return Arrays.asList(
                Category.builder().name("test sanguin").isActive(true).build(),
                Category.builder().name("evaluation").isActive(true).build(),
                Category.builder().name("").isActive(true).build()
        );
    }
}
