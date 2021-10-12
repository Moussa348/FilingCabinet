package com.keita.filingcabinet.mockData;

import com.keita.filingcabinet.model.entity.Category;
import com.keita.filingcabinet.model.enums.Role;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public abstract class CategoryMockData {

    public static List<Category> getListCategoryForRepoTest(){
        return Arrays.asList(
                Category.builder()
                        .id("6165be30967fd687010acff3")
                        .name("test sanguin")
                        .createdBy(Collections.singletonMap("employee1", Role.SUDO.toString()))
                        .creationDate(LocalDateTime.now())
                        .isActive(true)
                        .build(),
                Category.builder()
                        .id("6165be3be8435a3a5234e7c6")
                        .name("evaluation")
                        .createdBy(Collections.singletonMap("employee1", Role.SUDO.toString()))
                        .creationDate(LocalDateTime.now())
                        .isActive(true)
                        .build()
        );
    }
}
