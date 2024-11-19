package com.nasya.blog.controller;

import com.nasya.blog.entity.Post;
import com.nasya.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Post> get(){
        return postService.getPosts();
    }

    @GetMapping("/{slug}")
    public Post getBySlug(@PathVariable String slug){
      return postService.getPostBySlug(slug);
    }

    @PostMapping(path = "/")
    public Post create(@RequestBody Post post){
        return postService.createPost(post);
    }

    @PutMapping(path = "/{slug}")
    public Post updateBySlug(@PathVariable String slug, @RequestBody Post post){

        return postService.updatePostBySlug(slug, post);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteById(@PathVariable Integer id){
        return postService.deletePostById(id);
    }

    @PostMapping(path = "/{id}/publish")
    public Post publishById(@PathVariable Integer id){
        return postService.publishPost(id);
    }
}
