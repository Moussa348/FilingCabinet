package com.keita.filingcabinet.controller;

import com.keita.filingcabinet.exception.CategoryNotFoundException;
import com.keita.filingcabinet.service.CategoryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/createCategory/{name}")
    public String createCategory(@PathVariable String name) {
        return categoryService.createCategory(name);
    }

    @PatchMapping("/disable/{id}")
    public String disable(@PathVariable String id) throws CategoryNotFoundException {
        return categoryService.disable(id);
    }

    @PatchMapping("/enable/{id}")
    public String enable(@PathVariable String id) throws CategoryNotFoundException {
        return categoryService.enable(id);
    }

}
