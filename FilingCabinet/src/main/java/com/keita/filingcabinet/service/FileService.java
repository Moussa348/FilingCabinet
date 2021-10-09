package com.keita.filingcabinet.service;

import com.keita.filingcabinet.exception.AppropriateFileException;
import com.keita.filingcabinet.exception.FileNotFoundException;
import com.keita.filingcabinet.mapping.FileMapper;
import com.keita.filingcabinet.model.dto.FileCreation;
import com.keita.filingcabinet.model.entity.File;
import com.keita.filingcabinet.util.FileUtil;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@Service
public class FileService {

    @Autowired
    private GridFsTemplate gridFsTemplate;

    public String upload(FileCreation fileCreation) throws IOException, AppropriateFileException {
        MultipartFile multipartFile = fileCreation.getMultipartFile();

        if (FileUtil.isAnAcceptableFile(Objects.requireNonNull(multipartFile.getOriginalFilename()))) {
            File file = FileMapper.toFile(fileCreation);

            //TODO add LogService.addLog(OperationType.WRITE)
            return gridFsTemplate.store(multipartFile.getInputStream(), multipartFile.getOriginalFilename(), multipartFile.getContentType(), file).toString();
        }

        throw new AppropriateFileException("This is not an appropriate file!");
    }

    public ByteArrayResource download(GridFSFile gridFSFile) throws IOException {
        //TODO add LogService.addLog(OperationType.READ)
        return new ByteArrayResource(IOUtils.toByteArray(gridFsTemplate.getResource(gridFSFile).getInputStream()));
    }

    public GridFSFile getGridFsFile(String id) throws FileNotFoundException {
        GridFSFile gridFSFile = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)));

        if (gridFSFile != null)
            return gridFSFile;

        throw new FileNotFoundException("the file requested can't be found");
    }

    /*

    public List<FileDetail> getListFileDetail(String documentId){

    }
     */

}
