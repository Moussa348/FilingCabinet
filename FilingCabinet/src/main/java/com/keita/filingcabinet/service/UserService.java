package com.keita.filingcabinet.service;

import com.keita.filingcabinet.mapping.FileMapper;
import com.keita.filingcabinet.model.dto.FileDetailUserView;
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
public class UserService {

    @Autowired
    private FileService fileService;


    public List<FileDetailUserView> getListFileDetailUserView(PagingRequest pagingRequest) {
        return fileService.getListFile(new Query()
                .addCriteria(Criteria.where("metadata.folderId").is(pagingRequest.getFolderId()))
                .addCriteria(Criteria.where("metadata.isActive").is(true))
                .with(PageRequest.of(pagingRequest.getNoPage(), pagingRequest.getSize(), Sort.by("uploadDate"))))
                .stream().map(FileMapper::toFileDetailUserView).collect(Collectors.toList());
    }
}
