package com.keita.filingcabinet.repository;

import com.keita.filingcabinet.model.entity.File;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends MongoRepository<File,String> {
    boolean existsByFileNameAndFolderId(String fileName,String folderId);
    int countAllByFolderId(String folderId);//TODO --> test
}
