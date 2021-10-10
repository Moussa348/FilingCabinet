package com.keita.filingcabinet;

import com.keita.filingcabinet.model.entity.File;
import com.keita.filingcabinet.model.entity.Folder;
import com.keita.filingcabinet.repository.FolderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Order(1)
@Component
public class DbInit implements CommandLineRunner {

    private final FolderRepository folderRepository;

    private final GridFsTemplate gridFsTemplate;

    private final ResourceLoader resourceLoader;

    public DbInit(FolderRepository folderRepository, GridFsTemplate gridFsTemplate, ResourceLoader resourceLoader) {
        this.folderRepository = folderRepository;
        this.gridFsTemplate = gridFsTemplate;
        this.resourceLoader = resourceLoader;
    }

    private void insertFolders(){
        folderRepository.deleteAll();

        List<Folder> folders = Arrays.asList(
                Folder.builder().id("61621ca50545544ead443f75").build()
        );

        folderRepository.saveAll(folders);
    }

    private void insertFiles() throws IOException {
        File file = File.builder()
                .id("61624fa4ab9074f275e6fa96")
                .folderId("61621ca50545544ead443f75")
                .description("none")
                .uploadBy("employee1")
                .build();
        gridFsTemplate.store(resourceLoader.getResource("classpath:static/cv.txt").getInputStream(),"cv.txt","text/plain",file);
    }

    @Override
    public void run(String... args) throws Exception {
        insertFolders();
        insertFiles();
    }
}
