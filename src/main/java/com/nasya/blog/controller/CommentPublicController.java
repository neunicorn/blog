package com.nasya.blog.controller;

import com.nasya.blog.model.request.comment.CreateCommentRequest;
import com.nasya.blog.model.response.comment.CreateCommentResponse;
import com.nasya.blog.model.response.comment.GetCommentResponse;
import com.nasya.blog.services.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/public/comments")
public class CommentPublicController {

    @Autowired
    private CommentService commentService;


    @GetMapping
    public List<GetCommentResponse> getComments(@RequestParam(name = "postSlug", required = true) String postSlug,
                                                @RequestParam(name = "pageNo", required = false, defaultValue = "0") Integer pageNo,
                                                @RequestParam(name = "limit", required = false, defaultValue = "10") Integer limit){
        return commentService.getComments(postSlug, pageNo, limit);
    }

    @PostMapping
    public CreateCommentResponse createComment(@Valid  @RequestBody CreateCommentRequest request){
        return commentService.createPost(request);
    }

}
