package com.nasya.blog.repository;

import com.nasya.blog.entity.Post;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@SpringBootTest
public class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    void setUp(){
        postRepository.deleteAll();
    }

    @Test
    void findByIsDelete_givenNoPosts_sholudReturnEmpty(){

        List<Post> posts = postRepository.findAllByIsDeleted(false,
                PageRequest.of(0, 10)).getContent();
        Assertions.assertNotNull(posts);
        Assertions.assertEquals(0, posts.size());

    }

    @Test
    void findByIsDelete_givenTwoPostOnlyOneDeleted_sholudReturnOnePost(){

        Post post1 = new Post();
        Post post2 = new Post();

        post1.setDeleted(true);
        post2.setDeleted(false);

        postRepository.save(post1);
        postRepository.save(post2);

        List<Post> posts = postRepository.findAllByIsDeleted(false,
                PageRequest.of(0, 10)).getContent();
        Assertions.assertNotNull(posts);
        Assertions.assertEquals(1, posts.size());
    }
}
