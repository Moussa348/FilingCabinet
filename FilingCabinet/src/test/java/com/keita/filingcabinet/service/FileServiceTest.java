package com.keita.filingcabinet.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

@ExtendWith(MockitoExtension.class)
public class FileServiceTest {

    @Mock
    GridFsTemplate gridFsTemplate;

    @InjectMocks
    FileService fileService;


}
