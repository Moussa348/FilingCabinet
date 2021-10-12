package com.keita.filingcabinet.controller;

import com.keita.filingcabinet.model.dto.FolderDetail;
import com.keita.filingcabinet.service.FolderService;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log
@RestController
@RequestMapping("/folder")
public class FolderController {

    private final FolderService folderService;

    public FolderController(FolderService folderService) {
        this.folderService = folderService;
    }

    @GetMapping("/getListFolderDetailByPatientId/{patientId}")
    public List<FolderDetail> getListFolderDetailByPatientId(@PathVariable String patientId) {
        return folderService.getListFolderDetailByPatientId(patientId);
    }

}
