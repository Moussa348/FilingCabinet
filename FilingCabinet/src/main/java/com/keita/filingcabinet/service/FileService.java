package com.keita.filingcabinet.service;

import com.keita.filingcabinet.exception.AppropriateFileException;
import com.keita.filingcabinet.exception.FileNotFoundException;
import com.keita.filingcabinet.mapping.FileMapper;
import com.keita.filingcabinet.model.dto.FileCreation;
import com.keita.filingcabinet.model.entity.File;
import com.keita.filingcabinet.model.enums.OperationType;
import com.keita.filingcabinet.util.FileUtil;
import com.mongodb.client.gridfs.model.GridFSFile;
import lombok.extern.java.Log;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Log
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
            logService.add(id, OperationType.UPLOAD);

            return id;
        }

        throw new AppropriateFileException("THIS IS NOT AN APPROPRIATE FILE!");
    }

    public ByteArrayResource download(GridFSFile gridFSFile) throws IOException {
        logService.add(gridFSFile.getObjectId().toString(), OperationType.DOWNLOAD);

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

    public List<GridFSFile> getListFile(Query query) {

        return gridFsTemplate.find(query).into(new ArrayList<>());
    }

    public void disable(String id) throws FileNotFoundException, IOException {
        GridFSFile gridFSFile = getGridFsFile(id);

        gridFSFile.getMetadata().put("isActive", false);
        gridFSFile.getMetadata().put("deactivationDate", LocalDateTime.now());

        String newId = gridFsTemplate.store(getInputStreamFromResource(gridFSFile), gridFSFile.getFilename(), gridFSFile.getMetadata()).toString();

        logService.add(newId, OperationType.DISABLE);

        gridFsTemplate.delete(new Query(Criteria.where("_id").is(gridFSFile.getObjectId())));
    }

    public void enable(String id) throws FileNotFoundException, IOException {
        GridFSFile gridFSFile = getGridFsFile(id);

        gridFSFile.getMetadata().put("isActive", true);

        String newId = gridFsTemplate.store(getInputStreamFromResource(gridFSFile), gridFSFile.getFilename(), gridFSFile.getMetadata()).toString();

        logService.add(newId, OperationType.ENABLE);

        gridFsTemplate.delete(new Query(Criteria.where("_id").is(gridFSFile.getObjectId())));
    }

    private InputStream getInputStreamFromResource(GridFSFile gridFSFile) throws IOException {
        return gridFsTemplate.getResource(gridFSFile).getInputStream();
    }

}
