package com.keita.filingcabinet.controller;

import com.keita.filingcabinet.mapping.FolderMapper;
import com.keita.filingcabinet.model.dto.FolderDetail;
import com.keita.filingcabinet.service.FolderService;
import lombok.extern.java.Log;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Log
@RestController
@RequestMapping("/folder")
public class FolderController {

    private final FolderService folderService;

    public FolderController(FolderService folderService) {
        this.folderService = folderService;
    }

    @GetMapping("/getListFolderDetailByPatientId/{patientId}")
    @PreAuthorize("hasAnyAuthority('SUDO','USER')")
    public List<FolderDetail> getListFolderByPatientId(@PathVariable String patientId) {
        return folderService.getListFolderByPatientId(patientId).stream()
                .map(FolderMapper::toFolderDetail)
                .collect(Collectors.toList());
    }

}
