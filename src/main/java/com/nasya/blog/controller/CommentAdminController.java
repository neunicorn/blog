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
@RequestMapping("/api/admin/comments")
public class CommentAdminController {

    @Autowired
    private CommentService commentService;


    @GetMapping(path = "/")
    public List<GetCommentResponse> getComments(@RequestParam(name = "postSlug", required = false) String postSlug,
                                                @RequestParam(name = "pageNo", required = false, defaultValue = "0") Integer pageNo,
                                                @RequestParam(name = "limit", required = false, defaultValue = "10") Integer limit){
        return commentService.getComments(postSlug, pageNo, limit);
    }

    @GetMapping(path = "/{id}")
    public GetCommentResponse getCommentById(@PathVariable Integer id){
        return commentService.getComment(id);
    }

    @PostMapping(path = "/")
    public CreateCommentResponse createComment(@Valid  @RequestBody CreateCommentRequest request){
        return commentService.createPost(request);
    }

}
