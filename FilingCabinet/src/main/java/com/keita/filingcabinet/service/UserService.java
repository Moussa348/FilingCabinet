package com.keita.filingcabinet.service;

import com.keita.filingcabinet.exception.WrongCredentialsException;
import com.keita.filingcabinet.mapping.CategoryMapper;
import com.keita.filingcabinet.mapping.FileMapper;
import com.keita.filingcabinet.model.dto.CategoryDetailUserView;
import com.keita.filingcabinet.model.dto.FileDetailUserView;
import com.keita.filingcabinet.model.dto.PagingRequest;
import com.keita.filingcabinet.model.entity.User;
import com.keita.filingcabinet.repository.UserRepository;
import com.keita.filingcabinet.validator.folder.FolderExistConstraint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FileService fileService;

    @Autowired
    private CategoryService categoryService;

    public List<FileDetailUserView> getListFileDetailUserView(PagingRequest pagingRequest, @FolderExistConstraint String folderId) {
        return fileService.getListFile(new Query()
                .addCriteria(Criteria.where("metadata.folderId").is(folderId))
                .addCriteria(Criteria.where("metadata.isActive").is(true))
                .with(PageRequest.of(pagingRequest.getNoPage(), pagingRequest.getSize(), Sort.by("uploadDate"))))
                .stream().map(FileMapper::toFileDetailUserView).collect(Collectors.toList());
    }

    public List<CategoryDetailUserView> getListCategoryDetailUserView() {
        return categoryService.getListCategory(true)
                .stream()
                .map(CategoryMapper::toCategoryDetailUserView)
                .collect(Collectors.toList());
    }

    public User findUserByEmailAndPassword(String email, String password) throws WrongCredentialsException {
        return userRepository.findByEmailAndPasswordAndIsActiveTrueAndIsAccountVerifiedTrue(email, password)
                .orElseThrow(() -> new WrongCredentialsException("Can't find user with this credentials!"));
    }

}
