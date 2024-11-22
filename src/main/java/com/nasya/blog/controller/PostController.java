package com.nasya.blog.controller;

import com.nasya.blog.entity.Post;
import com.nasya.blog.model.request.post.CreatePostRequest;
import com.nasya.blog.model.request.post.GetPostsRequest;
import com.nasya.blog.model.request.post.UpdatePostRequest;
import com.nasya.blog.model.response.post.*;
import com.nasya.blog.services.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GetPostResponse> getPosts(@RequestParam(required = false, defaultValue = "0") Integer pageNo,
                                          @RequestParam(required = false, defaultValue = "10") Integer limit){

        GetPostsRequest req = GetPostsRequest.builder().pageNo(pageNo).limit(limit).build();

        return postService.getPosts(req);
    }

    @GetMapping("/{slug}")
    public GetPostResponse getBySlug(@PathVariable String slug){
      return postService.getPostBySlug(slug);
    }

    @PostMapping(path = "/")
    public CreatePostResponse create(@Valid @RequestBody CreatePostRequest request){
        return postService.createPost(request);
    }

    @PutMapping(path = "/{slug}")
    public UpdateBySlugPostResponse updateBySlug(@PathVariable String slug,
                                                 @Valid @RequestBody UpdatePostRequest request){

        return postService.updatePostBySlug(slug, request);
    }

    @DeleteMapping(path = "/{id}")
    public DeletePostByIdResponse deleteById(@PathVariable Integer id){
        return postService.deletePostById(id);
    }

    @PostMapping(path = "/{id}/publish")
    public PublishPostResponse publishById(@PathVariable Integer id){
        return postService.publishPost(id);
    }
}
