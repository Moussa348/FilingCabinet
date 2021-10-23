package com.keita.filingcabinet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keita.filingcabinet.mockData.FileMockData;
import com.keita.filingcabinet.model.dto.PagingRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("dev")
@AutoConfigureDataMongo
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @Test
    @WithMockUser(authorities = {"USER"})
    void shouldGetListFileDetailUserView() throws Exception {
        //ARRANGE
        String folderId = "61621ca50545544ead443f75";
        PagingRequest pagingRequest = FileMockData.getPagingRequest();

        //ACT
        MvcResult mvcResult1 = mockMvc.perform(MockMvcRequestBuilders.get("/user/getListFileDetailUserView/"+folderId)
                .param("noPage", pagingRequest.getNoPage().toString())
                .param("size", pagingRequest.getSize().toString())
                .flashAttr("pagingRequest", pagingRequest)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        //ASSERT
        assertEquals(MockHttpServletResponse.SC_OK, mvcResult1.getResponse().getStatus());
    }

    @Test
    @WithAnonymousUser
    void shouldNotGetListFileDetailUserView() throws Exception {
        //ARRANGE
        String folderId = "61621ca50545544ead443f75";
        PagingRequest pagingRequest = FileMockData.getPagingRequest();

        //ACT
        MvcResult mvcResult1 = mockMvc.perform(MockMvcRequestBuilders.get("/user/getListFileDetailUserView/"+folderId)
                .param("noPage", pagingRequest.getNoPage().toString())
                .param("size", pagingRequest.getSize().toString())
                .flashAttr("pagingRequest", pagingRequest)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized()).andReturn();

        //ASSERT
        assertEquals(MockHttpServletResponse.SC_UNAUTHORIZED, mvcResult1.getResponse().getStatus());
    }

    @Test
    @WithMockUser(authorities = {"USER"})
    void shouldGetListCategoryDetailUserView() throws Exception {
        //ACT
        MvcResult mvcResult1 = mockMvc.perform(MockMvcRequestBuilders.get("/user/getListCategoryDetailUserView")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        //ASSERT
        assertEquals(MockHttpServletResponse.SC_OK, mvcResult1.getResponse().getStatus());
    }

    @Test
    @WithAnonymousUser
    void shouldNotGetListCategoryDetailUserView() throws Exception {
        //ACT
        MvcResult mvcResult1 = mockMvc.perform(MockMvcRequestBuilders.get("/user/getListCategoryDetailUserView")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized()).andReturn();

        //ASSERT
        assertEquals(MockHttpServletResponse.SC_UNAUTHORIZED, mvcResult1.getResponse().getStatus());
    }

}
