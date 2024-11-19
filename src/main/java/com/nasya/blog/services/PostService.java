package com.nasya.blog.services;

import com.nasya.blog.entity.Post;
import com.nasya.blog.repository.PostRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public List<Post> getPosts(){
        return postRepository.findAll();
    }

    public Post getPostBySlug(String slug){
        return postRepository.findBySlug(slug).orElse(null);
    }

    public Post createPost(Post post){
        postRepository.save(post);
        return post;
    }

    public Post updatePostBySlug(String slug, Post post){
        Post savedPost = postRepository.findBySlug(slug).orElse(null);
        if(savedPost == null) return null;

        savedPost.setTitle(post.getTitle());
        savedPost.setSlug(post.getSlug());
        postRepository.save(savedPost);

        return savedPost;
    }

    public String deletePostById(Integer id){
        Post searchPost = postRepository.findById(id).orElse(null);

        if(searchPost == null) return null;

        postRepository.delete(searchPost);

        return "post deleted successfully";
    }

    public Post publishPost(Integer id){
        Post searchPost = postRepository.findById(id).orElse(null);
        if(searchPost == null) return null;
        
        searchPost.setPublished(true);
        postRepository.save(searchPost);
        return searchPost;
    }
}
