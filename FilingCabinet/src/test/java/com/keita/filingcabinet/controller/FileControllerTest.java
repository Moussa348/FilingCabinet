package com.keita.filingcabinet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keita.filingcabinet.DbInit;
import com.keita.filingcabinet.mockData.FileMockData;
import com.keita.filingcabinet.model.dto.FileCreation;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Collections;

import static com.keita.filingcabinet.DbInit.*;
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

    @Autowired
    MongoTemplate mongoTemplate;

    @AfterAll()
    void clean() {
        mongoTemplate.dropCollection("fs.files");
        mongoTemplate.dropCollection("fs.chunks");
    }

    @Test
    @WithMockUser(authorities = {"SUDO","USER"})
    void shouldUpload() throws Exception {
        //ARRANGE
        FileCreation fileCreation = FileMockData.getFileCreation(FileMockData.getMockMultipartFile());
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();

        parameters.put("employee1", Collections.singletonList(fileCreation.getUploadBy().get("employee1")));

        //ACT
        MvcResult mvcResult1 = mockMvc.perform(MockMvcRequestBuilders.multipart("/file/upload").file(FileMockData.getMockMultipartFile())
                .param("folderId", fileCreation.getFolderId())
                .param("description", fileCreation.getDescription())
                .params(parameters)
                .flashAttr("fileCreation", fileCreation)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        //ASSERT
        assertEquals(MockHttpServletResponse.SC_OK, mvcResult1.getResponse().getStatus());
    }

    @Test
    @WithAnonymousUser
    void shouldNotUpload() throws Exception {
        //ARRANGE
        FileCreation fileCreation = FileMockData.getFileCreation(FileMockData.getMockMultipartFile());
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();

        parameters.put("employee1", Collections.singletonList(fileCreation.getUploadBy().get("employee1")));

        //ACT
        MvcResult mvcResult1 = mockMvc.perform(MockMvcRequestBuilders.multipart("/file/upload").file(FileMockData.getMockMultipartFile())
                .param("folderId", fileCreation.getFolderId())
                .param("description", fileCreation.getDescription())
                .params(parameters)
                .flashAttr("fileCreation", fileCreation)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized()).andReturn();

        //ASSERT
        assertEquals(MockHttpServletResponse.SC_UNAUTHORIZED, mvcResult1.getResponse().getStatus());
    }

    @Test
    @WithMockUser(authorities = {"SUDO","USER"})
    void shouldDownload() throws Exception {
        //ACT
        MvcResult mvcResult1 = mockMvc.perform(MockMvcRequestBuilders.get("/file/download/" + FILE_IDS_TEST.get(0))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        //ASSERT
        assertEquals(MockHttpServletResponse.SC_OK, mvcResult1.getResponse().getStatus());
    }

    @Test
    @WithAnonymousUser
    void shouldNotDownload() throws Exception {
        //ACT
        MvcResult mvcResult1 = mockMvc.perform(MockMvcRequestBuilders.get("/file/download/" + FILE_IDS_TEST.get(0))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized()).andReturn();

        //ASSERT
        assertEquals(MockHttpServletResponse.SC_UNAUTHORIZED, mvcResult1.getResponse().getStatus());
    }

    @Test
    @WithMockUser(authorities = {"SUDO"})
    void shouldDisable() throws Exception {
        //ARRANGE
        String id = "61624fd9f3650a5146adb4fc";

        //ACT
        MvcResult mvcResult1 = mockMvc.perform(MockMvcRequestBuilders.patch("/file/disable/" + FILE_IDS_TEST.get(0))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        //ASSERT
        assertEquals(MockHttpServletResponse.SC_OK, mvcResult1.getResponse().getStatus());
    }

    @Test
    @WithAnonymousUser
    void shouldNotDisable() throws Exception {
        //ARRANGE
        String id = "61624fd9f3650a5146adb4fc";

        //ACT
        MvcResult mvcResult1 = mockMvc.perform(MockMvcRequestBuilders.patch("/file/disable/" + FILE_IDS_TEST.get(0))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized()).andReturn();

        //ASSERT
        assertEquals(MockHttpServletResponse.SC_UNAUTHORIZED, mvcResult1.getResponse().getStatus());
    }

    @Test
    @WithMockUser(authorities = {"SUDO"})
    void shouldEnable() throws Exception {
        //ARRANGE
        String id = "61624fd9f3650a5146adb4fc";

        //ACT
        MvcResult mvcResult1 = mockMvc.perform(MockMvcRequestBuilders.patch("/file/enable/" + FILE_IDS_TEST.get(1))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        //ASSERT
        assertEquals(MockHttpServletResponse.SC_OK, mvcResult1.getResponse().getStatus());
    }

    @Test
    @WithAnonymousUser
    void shouldNotEnable() throws Exception {
        //ARRANGE
        String id = "61624fd9f3650a5146adb4fc";

        //ACT
        MvcResult mvcResult1 = mockMvc.perform(MockMvcRequestBuilders.patch("/file/enable/" + FILE_IDS_TEST.get(1))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized()).andReturn();

        //ASSERT
        assertEquals(MockHttpServletResponse.SC_UNAUTHORIZED, mvcResult1.getResponse().getStatus());
    }

}
