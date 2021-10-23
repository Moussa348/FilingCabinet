package com.keita.filingcabinet.service;

import com.keita.filingcabinet.mapping.FolderMapper;
import com.keita.filingcabinet.model.dto.FolderDetail;
import com.keita.filingcabinet.model.entity.Folder;
import com.keita.filingcabinet.repository.FolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public List<Folder> getListFolderByPatientId(String patientId) {
        return folderRepository.findAllByPatientId(patientId);
    }

    public Map<String, String> getMapFolderByPatientId(String patientId) {
        Map<String, String> mapFolders = new HashMap<>();

        getListFolderByPatientId(patientId).forEach(folder -> mapFolders.put(folder.getCategoryName(), folder.getId()));

        return mapFolders;
    }

}
