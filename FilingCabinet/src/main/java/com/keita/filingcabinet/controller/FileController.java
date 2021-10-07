package com.keita.filingcabinet.controller;

import com.keita.filingcabinet.exception.AppropriateFileException;
import com.keita.filingcabinet.exception.FileNotFoundException;
import com.keita.filingcabinet.service.FileService;
import com.mongodb.client.gridfs.model.GridFSFile;
import lombok.extern.java.Log;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;

@Log
@RestController
@RequestMapping("/file")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("file") @NotNull @Valid MultipartFile multipartFile) throws IOException, AppropriateFileException {
        return fileService.upload(multipartFile);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<ByteArrayResource> download(@PathVariable String id) throws IOException, FileNotFoundException {
        GridFSFile gridFSFile = fileService.getGridFsFile(id);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(gridFSFile.getMetadata().getString("_contentType")))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + gridFSFile.getFilename())
                .body(fileService.download(gridFSFile));
    }
}
