package com.keita.filingcabinet.controller;

import com.keita.filingcabinet.exception.CategoryNotFoundException;
import com.keita.filingcabinet.service.CategoryService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/createCategory/{name}")
    @PreAuthorize("hasAuthority('SUDO')")
    public String createCategory(@PathVariable String name) {
        return categoryService.createCategory(name);
    }

    @PatchMapping("/disable/{id}")
    @PreAuthorize("hasAuthority('SUDO')")
    public String disable(@PathVariable String id) throws CategoryNotFoundException {
        return categoryService.disable(id);
    }

    @PatchMapping("/enable/{id}")
    @PreAuthorize("hasAuthority('SUDO')")
    public String enable(@PathVariable String id) throws CategoryNotFoundException {
        return categoryService.enable(id);
    }

    @GetMapping("/getListCategoryName")
    @PreAuthorize("hasAnyAuthority('SUDO','USER')")
    public List<String> getListCategoryName(){
        return categoryService.getListCategoryName();
    }

}
