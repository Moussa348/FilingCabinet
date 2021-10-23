package com.keita.filingcabinet.controller;

import com.keita.filingcabinet.exception.AppropriateFileException;
import com.keita.filingcabinet.exception.FileNotFoundException;
import com.keita.filingcabinet.model.dto.FileCreation;
import com.keita.filingcabinet.service.FileService;
import com.mongodb.client.gridfs.model.GridFSFile;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@Log
@RestController
@AllArgsConstructor
@RequestMapping("/file")
public class FileController {

    private final FileService fileService;

    @PostMapping("/upload")
    @PreAuthorize("hasAnyAuthority('USER','SUDO')")
    public String upload(@Valid @ModelAttribute FileCreation fileCreation) throws IOException, AppropriateFileException {
        return fileService.upload(fileCreation);
    }

    @GetMapping("/download/{id}")
    @PreAuthorize("hasAnyAuthority('USER','SUDO')")
    public ResponseEntity<ByteArrayResource> download(@PathVariable String id) throws IOException, FileNotFoundException {
        GridFSFile gridFSFile = fileService.getGridFsFile(id);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(gridFSFile.getMetadata().getString("_contentType")))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + gridFSFile.getFilename())
                .body(fileService.download(gridFSFile));
    }

    @PatchMapping("/disable/{id}")
    @PreAuthorize("hasAuthority('SUDO')")
    public String disable(@PathVariable String id) throws FileNotFoundException, IOException {
        return fileService.disable(id);
    }

    @PatchMapping("/enable/{id}")
    @PreAuthorize("hasAuthority('SUDO')")
    public String enable(@PathVariable String id) throws FileNotFoundException, IOException {
        return fileService.enable(id);
    }

}
