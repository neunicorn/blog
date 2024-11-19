package com.nasya.blog.services;

import com.nasya.blog.entity.Post;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PostService {
    Post post = new Post(1, "MAKAN", "Makan itu sangat menyenangkan", "makan-menyenangkan", true, false, 1, 1);
    Post post2 = new Post(2, "Mandi Air Hangat", "Makan itu sangat menyenangkan", "mandi-menyenangkan", true, false, 1, 1);

    @Getter
    List<Post> posts = new ArrayList<>(Arrays.asList(post,post2));

    public Post getPostBySlug(String slug){
        return posts.stream().filter(post -> post.getSlug().equals(slug)).findFirst().orElse(null);
    }

    public Post createPost(Post post){
        posts.add(post);
        return post;
    }

    public Post updatePostBySlug(String slug, Post post){
        Post savedPost = posts.stream().filter(p -> p.getSlug().equals(slug)).findFirst().orElse(null);
        if(savedPost == null) return null;

        posts.remove(savedPost);
        savedPost.setTitle(post.getTitle());
        savedPost.setSlug(post.getSlug());
        posts.add(savedPost);

        return savedPost;
    }

    public String deletePostById(Integer id){
        Post searchPost = posts.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);

        if(searchPost == null) return null;

        posts.remove(searchPost);

        return "post deleted successfully";
    }

    public Post publishPost(Integer id){
        Post searchPost = posts.
                stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
        if(searchPost == null) return null;

        posts.remove(searchPost);
        searchPost.setPublished(true);
        posts.add(searchPost);

        return searchPost;
    }
}
