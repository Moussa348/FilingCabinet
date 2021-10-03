package com.keita.filingcabinet.controller;

import com.keita.filingcabinet.model.entity.File;
import com.mongodb.client.gridfs.model.GridFSFile;
import lombok.extern.java.Log;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

@Log
@RestController
@RequestMapping("/gridFsTest")
public class BinaryController {

    @Autowired
    private GridFsTemplate gridFsTemplate;

    @PostMapping("/saveFile")
    public void saveFile() throws FileNotFoundException {
        File file = new File();

        file.put("organisation", "Java TEchie");

        file.setUploadBy("Massou");

        InputStream inputStream = new FileInputStream("./docs/cv.txt");

        file.put("type", "data");

        String object = gridFsTemplate.store(inputStream, "cv.txt", "text/plain", file).toString();

        log.info(object);
    }

    @PostMapping("/saveMultipartFile")
    public void saveMultipartFile(@RequestParam("multipartFile")MultipartFile multipartFile) throws IOException {
        File file = new File();

        file.put("organisation", "Java TEchie");

        file.setUploadBy("Massou");

        file.put("type", "data");

        String object = gridFsTemplate.store(multipartFile.getInputStream(), multipartFile.getOriginalFilename(), "text/plain", file).toString();

        log.info(object);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<ByteArrayResource> download(@PathVariable String id) throws IOException {
        GridFSFile gridFSFile = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)));

        assert gridFSFile != null;
        log.info(gridFSFile.toString());

        IOUtils.toByteArray(gridFsTemplate.getResource(gridFSFile).getInputStream());

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(gridFSFile.getMetadata().getString("_contentType")))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + gridFSFile.getFilename())
                .body(new ByteArrayResource(IOUtils.toByteArray(gridFsTemplate.getResource(gridFSFile).getInputStream())));
    }
}
