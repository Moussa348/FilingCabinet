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
public class SuperUserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @Test
    @WithMockUser(authorities = {"SUDO"})
    void shouldGetListFileDetailSuperUserView() throws Exception {
        //ARRANGE
        PagingRequest pagingRequest = FileMockData.getPagingRequest();

        //ACT
        MvcResult mvcResult1 = mockMvc.perform(MockMvcRequestBuilders.get("/superUser/getListFileDetailSuperUserView")
                .param("folderId", pagingRequest.getFolderId())
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
    void shouldNotGetListFileDetailSuperUserView() throws Exception {
        //ARRANGE
        PagingRequest pagingRequest = FileMockData.getPagingRequest();

        //ACT
        MvcResult mvcResult1 = mockMvc.perform(MockMvcRequestBuilders.get("/superUser/getListFileDetailSuperUserView")
                .param("folderId", pagingRequest.getFolderId())
                .param("noPage", pagingRequest.getNoPage().toString())
                .param("size", pagingRequest.getSize().toString())
                .flashAttr("pagingRequest", pagingRequest)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized()).andReturn();

        //ASSERT
        assertEquals(MockHttpServletResponse.SC_UNAUTHORIZED, mvcResult1.getResponse().getStatus());
    }

    @Test
    @WithMockUser(authorities = {"SUDO"})
    void shouldGetListCategoryDetailUserView() throws Exception {
        //ACT
        MvcResult mvcResult1 = mockMvc.perform(MockMvcRequestBuilders.get("/superUser/getListCategoryDetailSuperUserView")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        //ASSERT
        assertEquals(MockHttpServletResponse.SC_OK, mvcResult1.getResponse().getStatus());
    }

    @Test
    @WithAnonymousUser
    void shouldNotGetListCategoryDetailUserView() throws Exception {
        //ACT
        MvcResult mvcResult1 = mockMvc.perform(MockMvcRequestBuilders.get("/superUser/getListCategoryDetailSuperUserView")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized()).andReturn();

        //ASSERT
        assertEquals(MockHttpServletResponse.SC_UNAUTHORIZED, mvcResult1.getResponse().getStatus());
    }

}
