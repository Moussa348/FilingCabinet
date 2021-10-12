package com.keita.filingcabinet.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {


    private final CategoryController categoryController;


    public CategoryController(CategoryController categoryController) {
        this.categoryController = categoryController;
    }
}
