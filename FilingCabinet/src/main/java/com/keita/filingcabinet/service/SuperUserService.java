package com.keita.filingcabinet.service;

import com.keita.filingcabinet.mapping.CategoryMapper;
import com.keita.filingcabinet.mapping.FileMapper;
import com.keita.filingcabinet.model.dto.CategoryDetailSuperUserView;
import com.keita.filingcabinet.model.dto.FileDetailSuperUserView;
import com.keita.filingcabinet.model.dto.PagingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SuperUserService {

    @Autowired
    private FileService fileService;

    @Autowired
    private CategoryService categoryService;

    public List<FileDetailSuperUserView> getListFileDetailSuperUserView(PagingRequest pagingRequest,String folderId) {
        return fileService.getListFile(new Query()
                .addCriteria(Criteria.where("metadata.folderId").is(folderId))
                .with(PageRequest.of(pagingRequest.getNoPage(), pagingRequest.getSize(), Sort.by("uploadDate"))))
                .stream().map(FileMapper::toFileDetailSuperUserView).collect(Collectors.toList());
    }

    public List<CategoryDetailSuperUserView> getListCategoryDetailSuperUserView() {
        return categoryService.getListCategory(false)
                .stream()
                .map(CategoryMapper::toCategoryDetailSuperUserView)
                .collect(Collectors.toList());
    }

}
