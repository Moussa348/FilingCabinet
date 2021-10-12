package com.keita.filingcabinet;

import com.keita.filingcabinet.model.entity.File;
import com.keita.filingcabinet.model.entity.Folder;
import com.keita.filingcabinet.model.enums.Role;
import com.keita.filingcabinet.repository.FolderRepository;
import com.keita.filingcabinet.repository.LogRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Order(1)
@Component
public class DbInit implements CommandLineRunner {

    public static String FILE_ID_TEST;

    private final FolderRepository folderRepository;

    private final LogRepository logRepository;

    private final GridFsTemplate gridFsTemplate;

    private final ResourceLoader resourceLoader;

    private final MongoTemplate mongoTemplate;

    public DbInit(FolderRepository folderRepository, LogRepository logRepository, GridFsTemplate gridFsTemplate, ResourceLoader resourceLoader, MongoTemplate mongoTemplate) {
        this.folderRepository = folderRepository;
        this.logRepository = logRepository;
        this.gridFsTemplate = gridFsTemplate;
        this.resourceLoader = resourceLoader;
        this.mongoTemplate = mongoTemplate;
    }
    private void deleteCollections(){
        logRepository.deleteAll();
        folderRepository.deleteAll();

        mongoTemplate.dropCollection("fs.files");
        mongoTemplate.dropCollection("fs.chunks");
        mongoTemplate.dropCollection("users");
    }
    private void insertFolders() {
        List<Folder> folders = Arrays.asList(
                Folder.builder().id("61621ca50545544ead443f75").build()
        );

        folderRepository.saveAll(folders);
    }

    private void insertFiles() throws IOException {
        File file = File.builder()
                .folderId("61621ca50545544ead443f75")
                .description("none")
                .uploadBy(Collections.singletonMap("employee1", Role.USER.toString()))
                .isActive(true)
                .build();

        FILE_ID_TEST = gridFsTemplate.store(resourceLoader.getResource("classpath:static/cv.txt").getInputStream(), "cv.txt", "text/plain", file).toString();
    }

    @Override
    public void run(String... args) throws Exception {
        deleteCollections();
        insertFolders();
        insertFiles();
    }
}
