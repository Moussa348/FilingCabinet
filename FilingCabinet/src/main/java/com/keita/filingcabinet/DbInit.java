package com.keita.filingcabinet;

import com.keita.filingcabinet.model.entity.Folder;
import com.keita.filingcabinet.repository.FolderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Order(1)
@Component
public class DbInit implements CommandLineRunner {

    private final FolderRepository folderRepository;

    public DbInit(FolderRepository folderRepository) {
        this.folderRepository = folderRepository;
    }

    private void insertFolders(){
        folderRepository.deleteAll();

        List<Folder> folders = Arrays.asList(
                Folder.builder().build()
        );

        folderRepository.saveAll(folders);
    }

    @Override
    public void run(String... args) throws Exception {
        insertFolders();
    }
}
