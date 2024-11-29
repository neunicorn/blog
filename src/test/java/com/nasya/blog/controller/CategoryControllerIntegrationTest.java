package com.nasya.blog.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CategoryControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void getCategoryTestSuccess() throws Exception {
        mockMvc.perform( get("/api/public/categories")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andExpect(content().json("""
                []
        """, true));
    }

    void getCategoryTestSuccess_getOneCategory() throws Exception {
        mockMvc.perform( get("/api/public/categories")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andExpect(content().json("""
                [
                "id" , 1,
                "name" , "General"
                ]
        """, true));
    }
}
