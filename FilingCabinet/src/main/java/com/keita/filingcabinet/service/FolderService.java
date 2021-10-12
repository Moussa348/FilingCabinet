package com.keita.filingcabinet.service;

import com.keita.filingcabinet.model.entity.Folder;
import com.keita.filingcabinet.repository.FolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FolderService {

    @Autowired
    private FolderRepository folderRepository;

    public String createFolder(String patientId, String categoryName) {
        return folderRepository.save(
                Folder.builder()
                        .patientId(patientId)
                        .categoryName(categoryName)
                        .creationDate(LocalDateTime.now())
                        .build()
        ).getId();
    }

    public List<Folder> getListFolderDetailByPatientId(String patientId){
        return folderRepository.findAllByPatientId(patientId);
    }

}
