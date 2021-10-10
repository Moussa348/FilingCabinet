package com.keita.filingcabinet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keita.filingcabinet.mockData.FileMockData;
import com.keita.filingcabinet.model.dto.FileCreation;
import com.keita.filingcabinet.model.dto.PagingRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@AutoConfigureDataMongo
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FileControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @Test
    void upload() throws Exception {
        //ARRANGE
        FileCreation fileCreation = FileMockData.getFileCreation(FileMockData.getMockMultipartFile());

        //ACT
        MvcResult mvcResult1 = mockMvc.perform(MockMvcRequestBuilders.multipart("/file/upload").file(FileMockData.getMockMultipartFile())
                .param("folderId", fileCreation.getFolderId())
                .param("description", fileCreation.getDescription())
                .param("uploadBy", fileCreation.getUploadBy())
                .flashAttr("fileCreation", fileCreation)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        //ASSERT
        assertEquals(MockHttpServletResponse.SC_OK,mvcResult1.getResponse().getStatus());
    }

    @Test
    void download() throws Exception {
        //ARRANGE
        String id = "61624fd9f3650a5146adb4fc";

        //ACT
        MvcResult mvcResult1 = mockMvc.perform(MockMvcRequestBuilders.get("/file/download/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        //ASSERT
        assertEquals(MockHttpServletResponse.SC_OK,mvcResult1.getResponse().getStatus());
    }

    @Test
    void getListFileDetail() throws Exception {
        //ARRANGE
        PagingRequest pagingRequest = FileMockData.getPagingRequest();

        //ACT
        MvcResult mvcResult1 = mockMvc.perform(MockMvcRequestBuilders.get("/file/getListFileDetail")
                .param("folderId", pagingRequest.getFolderId())
                .param("noPage", pagingRequest.getNoPage().toString())
                .param("size", pagingRequest.getSize().toString())
                .flashAttr("pagingRequest", pagingRequest)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        //ASSERT
        assertEquals(MockHttpServletResponse.SC_OK,mvcResult1.getResponse().getStatus());
    }
}
