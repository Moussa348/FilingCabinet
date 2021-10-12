package com.keita.filingcabinet.service;

import com.keita.filingcabinet.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    //TODO --> when disable add name of the user that disble it and date
}
