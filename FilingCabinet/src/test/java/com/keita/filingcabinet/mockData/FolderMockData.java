package com.keita.filingcabinet.mockData;

import com.keita.filingcabinet.model.entity.Folder;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class FolderMockData {

    public static Folder getFolder() {
        return Folder.builder()
                .id("6165d08f188bee6fb9175d56")
                .patientId("6165d0a30bbd8500c4fcc0c2")
                .categoryName("EVALUATION")
                .creationDate(LocalDateTime.now())
                .build();
    }

    public static List<Folder> getListFolder() {
        return Arrays.asList(
                Folder.builder()
                        .id("6165d321a68e3070e01c44cb")
                        .patientId("6165d33784875473c6d85b66")
                        .categoryName("PRISE DE SANG")
                        .creationDate(LocalDateTime.now())
                        .build(),
                Folder.builder()
                        .id("6165d3293def0ed1a068d2eb")
                        .patientId("6165d33784875473c6d85b66")
                        .categoryName("EVALUATION")
                        .creationDate(LocalDateTime.now())
                        .build(),
                Folder.builder()
                        .id("6165d32f61047c7e904e8b2b")
                        .patientId("6165d341ae6df7cbbdb53eab")
                        .categoryName("CHIRURGIE")
                        .creationDate(LocalDateTime.now())
                        .build()
        );
    }

    public static Map<String,String> getMapFolders(){
        Map<String,String> mapFolders = new HashMap<>();

        mapFolders.put("6165d321a68e3070e01c44cb","EVALUATION");
        mapFolders.put("6165d3293def0ed1a068d2eb","EVALUATION");
        mapFolders.put("6165d32f61047c7e904e8b2b","CHIRURGIE");

        return mapFolders;
    }

}
