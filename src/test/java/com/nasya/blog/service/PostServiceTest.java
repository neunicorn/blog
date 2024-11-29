package com.nasya.blog.service;

import com.nasya.blog.entity.Post;
import com.nasya.blog.mapper.PostMapper;
import com.nasya.blog.model.request.post.CreatePostRequest;
import com.nasya.blog.model.response.post.CreatePostResponse;
import com.nasya.blog.repository.PostRepository;
import com.nasya.blog.services.PostService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;
import java.time.Instant;

@SpringBootTest
@AutoConfigureMockMvc
public class PostServiceTest {

    @Autowired
    @InjectMocks
    private PostService postService;

    @Mock
    private PostRepository postRepository;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp(){
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void setDown() throws Exception {
        closeable.close();
    }


    @Test
    void createPost_givenValid_shouldReturnOk(){

        CreatePostRequest request = new CreatePostRequest();
        request.setTitle("Nasi Padang");
        request.setBody("Nasi Padang adalah makanan khas padang yang merupakan salah satu daerah di Pulau Sumatra Indonesia");
        request.setSlug("nasi-padang");

        Post post = PostMapper.INSTANCE.mapCreatePostRequest(request);
        post.setCreatedAt(BigInteger.valueOf(Instant.now().getEpochSecond()));
        post.setUpdatedAt(BigInteger.valueOf(Instant.now().getEpochSecond()));
        post.setCommentCount(0);
        Mockito.when(postRepository.save(post)).thenReturn(post);

        CreatePostResponse res = postService.createPost(request);

        Assertions.assertNotNull(res);
        Assertions.assertEquals("Nasi Padang", res.getTitle());
        Assertions.assertEquals("nasi-padang", res.getSlug());
        Assertions.assertEquals("Nasi Padang adalah makanan khas padang yang merupakan salah satu daerah di Pulau Sumatra Indonesia",
                res.getBody());
        Assertions.assertEquals(0, res.getCommentCount());
    }
}

