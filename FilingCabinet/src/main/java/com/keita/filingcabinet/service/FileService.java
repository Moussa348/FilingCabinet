package com.keita.filingcabinet.service;

import com.keita.filingcabinet.exception.AppropriateFileException;
import com.keita.filingcabinet.exception.FileNotFoundException;
import com.keita.filingcabinet.mapping.FileMapper;
import com.keita.filingcabinet.model.dto.FileCreation;
import com.keita.filingcabinet.model.dto.FileDetail;
import com.keita.filingcabinet.model.dto.PagingRequest;
import com.keita.filingcabinet.model.entity.File;
import com.keita.filingcabinet.model.enums.OperationType;
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
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class FileService {

    @Autowired
    private GridFsTemplate gridFsTemplate;

    @Autowired
    private LogService logService;

    public String upload(FileCreation fileCreation) throws IOException, AppropriateFileException {
        MultipartFile multipartFile = fileCreation.getMultipartFile();

        if (FileUtil.isAnAcceptableFile(Objects.requireNonNull(multipartFile.getOriginalFilename()))) {
            File file = FileMapper.toFile(fileCreation);

            String id = gridFsTemplate.store(multipartFile.getInputStream(), multipartFile.getOriginalFilename(), multipartFile.getContentType(), file).toString();

            //TODO --> getName from security context
            logService.add("", id, OperationType.WRITE);

            return id;
        }

        throw new AppropriateFileException("THIS IS NOT AN APPROPRIATE FILE!");
    }

    public ByteArrayResource download(GridFSFile gridFSFile) throws IOException {
        logService.add("", gridFSFile.getObjectId().toString(), OperationType.READ);

        return new ByteArrayResource(IOUtils.toByteArray(getInputStreamFromResource(gridFSFile)));
    }

    public GridFSFile getGridFsFile(String id) throws FileNotFoundException {
        GridFSFile gridFSFile = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)));
        if (gridFSFile != null) {
            //TODO --> getName from security context
            return gridFSFile;
        }

        throw new FileNotFoundException("THE FILE REQUESTED CAN NOT BE FOUND!");
    }

    public List<FileDetail> getListFileDetail(PagingRequest pagingRequest) {
        List<GridFSFile> gridFSFiles = new ArrayList<>();

        return gridFsTemplate.find(
                new Query(Criteria.where("metadata.folderId").is(pagingRequest.getFolderId()))
                        .addCriteria(Criteria.where("metadata.isActive").is(true))
                        .with(PageRequest.of(pagingRequest.getNoPage(), pagingRequest.getSize(), Sort.by("uploadDate")))
        ).into(gridFSFiles).stream().map(FileMapper::toFileDetail).collect(Collectors.toList());
    }

    public void disable(String id) throws FileNotFoundException, IOException {
        GridFSFile gridFSFile = getGridFsFile(id);

        gridFSFile.getMetadata().put("isActive", false);
        gridFSFile.getMetadata().put("hasBeenUpdated", true);

        gridFsTemplate.store(getInputStreamFromResource(gridFSFile), gridFSFile.getFilename(), gridFSFile.getMetadata());

        gridFsTemplate.delete(new Query(Criteria.where("_id").is(gridFSFile.getObjectId())));
    }

    public void enable(String id) throws FileNotFoundException, IOException {
        GridFSFile gridFSFile = getGridFsFile(id);

        gridFSFile.getMetadata().put("isActive", true);
        gridFSFile.getMetadata().put("hasBeenUpdated", true);

        gridFsTemplate.store(getInputStreamFromResource(gridFSFile), gridFSFile.getFilename(), gridFSFile.getMetadata());

        gridFsTemplate.delete(new Query(Criteria.where("_id").is(gridFSFile.getObjectId())));
    }

    private InputStream getInputStreamFromResource(GridFSFile gridFSFile) throws IOException {
        return gridFsTemplate.getResource(gridFSFile).getInputStream();
    }

}
