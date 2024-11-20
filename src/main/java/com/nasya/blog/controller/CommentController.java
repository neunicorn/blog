package com.nasya.blog.controller;

import com.nasya.blog.entity.Comment;
import com.nasya.blog.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;


    @GetMapping(path = "/")
    public List<Comment> getComments(@RequestParam(name = "postSlug", required = true) String postSlug,
                                     @RequestParam(name = "page_no", required = false, defaultValue = "0") Integer page_no,
                                     @RequestParam(name = "limit", required = false, defaultValue = "10") Integer limit){
        return commentService.getComments(postSlug, page_no, limit);
    }

    @GetMapping(path = "/{id}")
    public Comment getCommentById(@PathVariable Integer id){
        return commentService.getComment(id);
    }

    @PostMapping(path = "/{id}")
    public Comment createComment(@RequestBody Comment comment, @PathVariable Integer id){
        return commentService.createPost(comment, id);
    }

}
