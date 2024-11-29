package com.nasya.blog.controller;

import com.nasya.blog.repository.PostRepository;
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
public class PostPublicControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PostPublicController postPublicController;

    @Autowired
    private PostRepository  postRepository;


    void testGetPostBySlug() throws Exception {
        mockMvc.perform(
                get("/api/public/category")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(status().isOk()).andDo(result -> {
                    result.getResponse().getContentAsString(); 
                });
    }

}

