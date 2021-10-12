package com.keita.filingcabinet.service;

import com.keita.filingcabinet.model.entity.Category;
import com.keita.filingcabinet.model.enums.Role;
import com.keita.filingcabinet.repository.CategoryRepository;
import com.keita.filingcabinet.validator.category.CategoryExistConstraint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public String createCategory(@CategoryExistConstraint String name) {
        //TODO --> add the email of user connected
        //TODO --> add FolderService.createFolder for loop in PatientService.getListPatientId
        return categoryRepository.save(Category.builder()
                .name(name)
                .createdBy(Collections.singletonMap("employee1", Role.SUDO.toString()))
                .creationDate(LocalDateTime.now())
                .isActive(true)
                .build()
        ).getId();
    }

    public List<Category> getListCategory(Boolean isActive){
        return isActive ? categoryRepository.findAllByIsActiveTrue(): categoryRepository.findAll();
    }

    public List<String> getListCategoryName(){
        return getListCategory(true).stream().map(Category::getName).collect(Collectors.toList());
    }

    //TODO --> when disable add name of the user that disble it and date
}
