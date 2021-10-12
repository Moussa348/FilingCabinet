package com.keita.filingcabinet;

import com.keita.filingcabinet.model.entity.File;
import com.keita.filingcabinet.model.entity.Folder;
import com.keita.filingcabinet.model.enums.Role;
import com.keita.filingcabinet.repository.FileRepository;
import com.keita.filingcabinet.repository.FolderRepository;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Order(1)
@Component
public class DbInit implements CommandLineRunner {

    private final FolderRepository folderRepository;

    private final GridFsTemplate gridFsTemplate;

    private final ResourceLoader resourceLoader;

    private final MongoTemplate mongoTemplate;

    public DbInit(FolderRepository folderRepository, GridFsTemplate gridFsTemplate, ResourceLoader resourceLoader, MongoTemplate mongoTemplate) {
        this.folderRepository = folderRepository;
        this.gridFsTemplate = gridFsTemplate;
        this.resourceLoader = resourceLoader;
        this.mongoTemplate = mongoTemplate;
    }

    private void insertFolders() {

        folderRepository.deleteAll();

        List<Folder> folders = Arrays.asList(
                Folder.builder().id("61621ca50545544ead443f75").build()
        );

        folderRepository.saveAll(folders);
    }

    private void insertFiles() throws IOException {
        mongoTemplate.dropCollection("fs.files");
        mongoTemplate.dropCollection("fs.chunks");

        File file = File.builder()
                .folderId("61621ca50545544ead443f75")
                .description("none")
                .uploadBy(Collections.singletonMap("employee1", Role.USER.toString()))
                .isActive(true)
                .build();

        gridFsTemplate.store(resourceLoader.getResource("classpath:static/cv.txt").getInputStream(), "cv.txt", "text/plain", file);
    }

    @Override
    public void run(String... args) throws Exception {
        insertFolders();
        insertFiles();
    }
}
