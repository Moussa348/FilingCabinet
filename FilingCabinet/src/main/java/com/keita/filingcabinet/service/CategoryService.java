package com.keita.filingcabinet.service;

import com.keita.filingcabinet.exception.CategoryNotFoundException;
import com.keita.filingcabinet.model.entity.Category;
import com.keita.filingcabinet.repository.CategoryRepository;
import com.keita.filingcabinet.security.OwnershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public String createCategory(String name) {
        //TODO --> add FolderService.createFolder for loop in PatientService.getListPatientId
        return categoryRepository.existsByName(name) ? "" : categoryRepository.save(Category.builder()
                .name(name)
                .createdBy(OwnershipService.getCurrentUserDetails())
                .creationDate(LocalDateTime.now())
                .isActive(true)
                .build()
        ).getId();
    }

    public List<Category> getListCategory(Boolean isActive) {
        return isActive ? categoryRepository.findAllByIsActiveTrue() : categoryRepository.findAll();
    }

    public List<String> getListCategoryName() {
        return getListCategory(true).stream().map(Category::getName).collect(Collectors.toList());
    }

    public String disable(String id) throws CategoryNotFoundException {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Can't find categgory with this id!"));

        category.setIsActive(false);

        category.setDeactivatedBy(OwnershipService.getCurrentUserDetails());

        category.setDeactivationDate(LocalDateTime.now());

        return categoryRepository.save(category).getId();
    }

    public String enable(String id) throws CategoryNotFoundException {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Can't find categgory with this id!"));

        category.setIsActive(true);

        return categoryRepository.save(category).getId();
    }

}
