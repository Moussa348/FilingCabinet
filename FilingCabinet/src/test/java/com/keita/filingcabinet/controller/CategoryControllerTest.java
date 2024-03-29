package com.keita.filingcabinet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keita.filingcabinet.mockData.FileMockData;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("dev")
@AutoConfigureDataMongo
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CategoryControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    MongoTemplate mongoTemplate;

    @AfterAll()
    void clean() {
        mongoTemplate.dropCollection("categories");
    }

    @Test
    @WithMockUser(authorities = {"SUDO"})
    void shouldCreateCategory() throws Exception {
        //ARRANGE
        String name = "newCategory";

        //ACT
        MvcResult mvcResult1 = mockMvc.perform(MockMvcRequestBuilders.post("/category/createCategory/" + name)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        //ASSERT
        assertEquals(MockHttpServletResponse.SC_OK, mvcResult1.getResponse().getStatus());
    }

    @Test
    @WithAnonymousUser
    void createCategory() throws Exception {
        //ARRANGE
        String name = "newCategory";

        //ACT
        MvcResult mvcResult1 = mockMvc.perform(MockMvcRequestBuilders.post("/category/createCategory/" + name)
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
        String id = "6165cab909f5a9399b41a94a";

        //ACT
        MvcResult mvcResult1 = mockMvc.perform(MockMvcRequestBuilders.patch("/category/disable/" + id)
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
        String id = "6165cab909f5a9399b41a94a";

        //ACT
        MvcResult mvcResult1 = mockMvc.perform(MockMvcRequestBuilders.patch("/category/disable/" + id)
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
        String id = "6165cab909f5a9399b41a94a";

        //ACT
        MvcResult mvcResult1 = mockMvc.perform(MockMvcRequestBuilders.patch("/category/enable/" + id)
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
        String id = "6165cab909f5a9399b41a94a";

        //ACT
        MvcResult mvcResult1 = mockMvc.perform(MockMvcRequestBuilders.patch("/category/enable/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized()).andReturn();

        //ASSERT
        assertEquals(MockHttpServletResponse.SC_UNAUTHORIZED, mvcResult1.getResponse().getStatus());
    }

    @Test
    @WithMockUser(authorities = {"SUDO"})
    void shouldGetListCategoryName() throws Exception {
        //ACT
        MvcResult mvcResult1 = mockMvc.perform(MockMvcRequestBuilders.get("/category/getListCategoryName/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        //ASSERT
        assertEquals(MockHttpServletResponse.SC_OK, mvcResult1.getResponse().getStatus());
    }

    @Test
    @WithAnonymousUser
    void shouldNotGetListCategoryName() throws Exception {
        //ACT
        MvcResult mvcResult1 = mockMvc.perform(MockMvcRequestBuilders.get("/category/getListCategoryName/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized()).andReturn();

        //ASSERT
        assertEquals(MockHttpServletResponse.SC_UNAUTHORIZED, mvcResult1.getResponse().getStatus());
    }
}
