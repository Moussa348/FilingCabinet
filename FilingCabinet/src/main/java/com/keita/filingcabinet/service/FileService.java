package com.keita.filingcabinet.service;

import com.keita.filingcabinet.exception.AppropriateFileException;
import com.keita.filingcabinet.exception.FileNotFoundException;
import com.keita.filingcabinet.mapping.FileMapper;
import com.keita.filingcabinet.model.dto.FileCreation;
import com.keita.filingcabinet.model.dto.FileDetail;
import com.keita.filingcabinet.model.dto.PagingRequest;
import com.keita.filingcabinet.model.entity.File;
import com.keita.filingcabinet.util.FileUtil;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class FileService {

    @Autowired
    private GridFsTemplate gridFsTemplate;

    public String upload(FileCreation fileCreation) throws IOException, AppropriateFileException {
        MultipartFile multipartFile = fileCreation.getMultipartFile();

        if (FileUtil.isAnAcceptableFile(Objects.requireNonNull(multipartFile.getOriginalFilename()))) {
            File file = FileMapper.toFile(fileCreation);

            String id = gridFsTemplate.store(multipartFile.getInputStream(), multipartFile.getOriginalFilename(), multipartFile.getContentType(), file).toString();

            //TODO add LogService.addLog(OperationType.WRITE)
            return id;
        }

        throw new AppropriateFileException("THIS IS NOT AN APPROPRIATE FILE!");
    }

    public ByteArrayResource download(GridFSFile gridFSFile) throws IOException {
        return new ByteArrayResource(IOUtils.toByteArray(gridFsTemplate.getResource(gridFSFile).getInputStream()));
    }

    public GridFSFile getGridFsFile(String id) throws FileNotFoundException {
        GridFSFile gridFSFile = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)));
        if (gridFSFile != null)
        //TODO add LogService.addLog(OperationType.READ)
            return gridFSFile;

        throw new FileNotFoundException("THE FILE REQUESTED CAN NOT BE FOUND!");
    }

    //TODO add add validator for noPage and size
    public List<FileDetail> getListFileDetail(PagingRequest pagingRequest) throws NumberFormatException {
        List<GridFSFile> gridFSFiles = new ArrayList<>();

        return gridFsTemplate.find(
                new Query(Criteria.where("metadata.folderId").is(pagingRequest.getFolderId()))
                        .with(PageRequest.of(pagingRequest.getNoPage(), pagingRequest.getSize(), Sort.by("uploadDate")))
        ).into(gridFSFiles).stream().map(FileMapper::toFileDetail).collect(Collectors.toList());
    }

}
