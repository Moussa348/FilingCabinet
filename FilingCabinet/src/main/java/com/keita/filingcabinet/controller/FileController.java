package com.keita.filingcabinet.controller;

import com.keita.filingcabinet.ExcludeCoverage;
import com.keita.filingcabinet.exception.AppropriateFileException;
import com.keita.filingcabinet.exception.FileNotFoundException;
import com.keita.filingcabinet.model.dto.FileCreation;
import com.keita.filingcabinet.model.dto.FileDetail;
import com.keita.filingcabinet.model.dto.PagingRequest;
import com.keita.filingcabinet.service.FileService;
import com.mongodb.client.gridfs.model.GridFSFile;
import lombok.extern.java.Log;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Log
@RestController
@RequestMapping("/file")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }


    @PostMapping("/upload")
    public String upload(@Valid @ModelAttribute FileCreation fileCreation) throws IOException, AppropriateFileException {
        return fileService.upload(fileCreation);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<ByteArrayResource> download(@PathVariable String id) throws IOException, FileNotFoundException {
        GridFSFile gridFSFile = fileService.getGridFsFile(id);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(gridFSFile.getMetadata().getString("_contentType")))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + gridFSFile.getFilename())
                .body(fileService.download(gridFSFile));
    }

    @GetMapping("/getListFileDetail")
    public List<FileDetail> getListFileDetail(@Valid @ModelAttribute PagingRequest pagingRequest){
        return fileService.getListFileDetail(pagingRequest);
    }

    @PatchMapping("/disable/{id}")
    public void disable(@PathVariable String id) throws FileNotFoundException, IOException {
        fileService.disable(id);
    }
}
