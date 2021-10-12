package com.keita.filingcabinet;

import com.keita.filingcabinet.model.entity.Category;
import com.keita.filingcabinet.model.entity.File;
import com.keita.filingcabinet.model.entity.Folder;
import com.keita.filingcabinet.model.enums.Role;
import com.keita.filingcabinet.repository.CategoryRepository;
import com.keita.filingcabinet.repository.FolderRepository;
import com.keita.filingcabinet.repository.LogRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Order(1)
@Component
public class DbInit implements CommandLineRunner {

    public static String FILE_ID_TEST;

    private final FolderRepository folderRepository;

    private final LogRepository logRepository;

    private final CategoryRepository categoryRepository;

    private final GridFsTemplate gridFsTemplate;

    private final ResourceLoader resourceLoader;

    private final MongoTemplate mongoTemplate;

    public DbInit(FolderRepository folderRepository, LogRepository logRepository, CategoryRepository categoryRepository, GridFsTemplate gridFsTemplate, ResourceLoader resourceLoader, MongoTemplate mongoTemplate) {
        this.folderRepository = folderRepository;
        this.logRepository = logRepository;
        this.categoryRepository = categoryRepository;
        this.gridFsTemplate = gridFsTemplate;
        this.resourceLoader = resourceLoader;
        this.mongoTemplate = mongoTemplate;
    }

    private void deleteCollections() {
        logRepository.deleteAll();
        folderRepository.deleteAll();
        categoryRepository.deleteAll();

        mongoTemplate.dropCollection("fs.files");
        mongoTemplate.dropCollection("fs.chunks");
        mongoTemplate.dropCollection("users");
    }

    private void insertFolders() {
        List<Folder> folders = Arrays.asList(
                Folder.builder()
                        .id("61621ca50545544ead443f75")
                        .categoryName("EVALUATION")
                        .isActive(true)
                        .build()
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

    private void insertCategories() {
        List<Category> categories = Arrays.asList(
                Category.builder()
                        .id("6165cab909f5a9399b41a94a")
                        .createdBy(Collections.singletonMap("employee1", Role.SUDO.toString()))
                        .creationDate(LocalDateTime.now())
                        .isActive(true)
                        .name("EVALUATION")
                        .build()
        );

        categoryRepository.saveAll(categories);
    }

    @Override
    public void run(String... args) throws Exception {
        deleteCollections();
        insertFolders();
        insertFiles();
        insertCategories();
    }
}
